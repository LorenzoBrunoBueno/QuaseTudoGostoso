import java.util.ArrayList;


import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;


public class Usuario implements HttpHandler, Comparable<Usuario> {
        int idusuario;
        String nome;
        String email;
        String data_nascimento;
        int cep;
        int genero;
        String senha;
        String salt;
        String inscrito;
        String uuid;

        public static ArrayList<Usuario> usuarios = new ArrayList<>();
        public ArrayList<Comentario> comentarios = new ArrayList<>();
        public ArrayList<Receita> receitas = new ArrayList<>();

    public Usuario(int idusuario,String nome, String email, String data_nascimento, int cep, int genero, String senha, String salt, String inscrito, String uuid ) throws Exception{
        this.idusuario = idusuario;
        this.nome = nome;
        this.email = email;
        this.data_nascimento = data_nascimento;
        this.cep = cep;
        this.genero = genero;
        this.senha = senha;
        this.salt = salt;
        this.inscrito = inscrito;
        this.uuid = uuid;

       
    }

      public Usuario(String nome, String email, String data_nascimento, int cep, int genero, String senha, String salt, String inscrito, String uuid ) throws Exception{
        this.nome = nome;
        this.email = email;
        this.data_nascimento = data_nascimento;
        this.cep = cep;
        this.genero = genero;
        this.senha = senha;
        this.salt = salt;
        this.inscrito = inscrito;
        this.uuid = uuid;

       
    }

    public void inserir() throws Exception{
         PreparedStatement stmt = DAO.createConnection().prepareStatement(
            "INSERT INTO usuario (nome, email, data_nascimento, cep, genero, senha, salt, inscrito, uuid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);"
        );
        stmt.setString(1, this.getNome());
        stmt.setString(2, this.getEmail());
        stmt.setString(3, this.getData_nascimento());
        stmt.setInt(4, this.getCep());
        stmt.setInt(5, this.getGenero());
        stmt.setString(6, this.getSenha());
        stmt.setString(7, this.getSalt());
        stmt.setString(8, this.getInscrito());
        stmt.setString(9, this.getUuid());
        stmt.execute();
        DAO.closeConnection();
    }

    public static void addUsuario(Usuario usuario){
        Usuario.usuarios.add(usuario);
    }

    @Override
    public String toString() {
        return
         "" +
        "Id:" + this.getID() + "\n" +
        "Nome:" + this.getNome() + "\n" +
        "Email:" + this.getEmail() + "\n" +
        "Data Nascimento:" + this.getData_nascimento() + "\n" +
        "CEP:" + this.getCep() + "\n" +
        "Gênero:" + this.getGenero() + "\n" +
        "Senha:" + this.getSenha() + "\n" +
        "Salt:" + this.getSalt() + "\n" +
        "Data Inscrição:" + this.getInscrito() + "\n" +
        "UUID:" + this.getUuid();
    }

    
    public static ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public Usuario(){

    }

    @Override
    public int compareTo(Usuario other) {
        // Example: Sort by 'nome' in ascending order
        return Integer.compare(idusuario, other.idusuario);
    }

    public int getID(){
        return idusuario;
    }


    public String getNome(){
        return nome;
    }

    public String getEmail(){
        return email;
    }

    public String getData_nascimento(){
        return data_nascimento;
    }

    public int getCep(){
        return cep;
    }

    public int getGenero(){
        return genero;
    }

    public String getSenha(){
        return senha;
    }
    
    public String getSalt(){
        return salt;
    }

    public String getInscrito(){
        return inscrito;
    }

    public String getUuid(){
        return uuid;
    }

    public void setID(int idusuario){
        this.idusuario = idusuario;
    }


    public void setNome(String nome){
        this.nome = nome;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setData_nascimento(String data_nascimento){
        this.data_nascimento = data_nascimento;
    }

    public void setCep(int cep){
        this.cep = cep;
    }

    public void setGenero(int genero){
        this.genero = genero;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }
    
    public void setSalt(String salt){
        this.salt = salt;
    }

    public void setInscrito(String inscrito){
        this.inscrito = inscrito;
    }
    
    public void setUuid(String uuid){
        this.uuid = uuid;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException{

        try{
            String method = exchange.getRequestMethod();
            if (method.equalsIgnoreCase("GET")) {
                handleGet(exchange);
            } else if (method.equalsIgnoreCase("POST")) {
                handlePost(exchange);
            } else {
                String response = "Método não suportado, Utilize apenas GET e POST!";
                byte[] bytes = response.getBytes("UTF-8");
                exchange.getResponseHeaders().add("Content-Type", "text/plain; charset=UTF-8");
                exchange.sendResponseHeaders(405, bytes.length);
                OutputStream os = exchange.getResponseBody();
                os.write(bytes);
                os.close();
            }
        }catch (Exception e){
            e.printStackTrace();
            String response = "Erro interno no servidor.";
            byte[] bytes = response.getBytes("UTF-8");
            exchange.getResponseHeaders().add("Content-Type", "text/plain; charset=UTF-8");
            exchange.sendResponseHeaders(500, bytes.length);

            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
        }

    }


    public void handleGet(HttpExchange exchange) throws IOException, Exception{
         StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario x = usuarios.get(i);
            json.append(String.format("{\"ID\": \"%s\", \"Nome Usuario\": \"%s\", \"Email\": \"%s\", \"Data de Nascimento\":\"%s\", \"CEP\":\"%s\", \"Genero\":\"%s\", \"Senha\":\"%s\", \"Salt\":\"Inscrito\":\"uuid\":\"%s\"}",
                    x.getID(), x.getNome(), x.getEmail(), x.getData_nascimento(), x.getCep(), x.getGenero(), x.getSenha(), x.getSalt(), x.getInscrito(), x.getUuid()));
            if (i < usuarios.size() - 1) json.append(",");
        }
        json.append("]");

        byte[] bytes = json.toString().getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(200, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

     private void handlePost(HttpExchange exchange) throws IOException, Exception {
        InputStream is = exchange.getRequestBody();
        String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);

        String nome = body.replaceAll(".*\"Nome\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        String email = body.replaceAll(".*\"Email\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        String data_nasci = body.replaceAll(".*\"Data Nascimento\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        String cep = body.replaceAll(".*\"CEP\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        String genero = body.replaceAll(".*\"Genero\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        String Senha = body.replaceAll(".*\"Senha\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        String salt = body.replaceAll(".*\"Salt\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        String inscrito = body.replaceAll(".*\"Inscrito\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        String uuid = body.replaceAll(".*\"UUID\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        int intcep = Integer.parseInt(cep);
        int intgenero = Integer.parseInt(genero);

          
        new Usuario(idusuario, nome, email, data_nasci, intcep, intgenero, Senha, salt, inscrito, uuid);

        String response = "{\"message\": \"Usuario adicionada com sucesso\"}";
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(201, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
            os.close();
        }
    }

}
