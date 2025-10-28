import java.util.ArrayList;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

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

    public Usuario(int idusuario, String nome, String email, String data_nascimento, int cep, int genero, String senha, String salt, String inscrito, String uuid ){
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

    public int setID(int idusuario){
        return idusuario;
    }


    public String setNome(String nome){
        return nome;
    }

    public String getEmail(String email){
        return email;
    }

    public String data_nascimento(String data_nascimento){
        return data_nascimento;
    }

    public int setCep(int cep){
        return cep;
    }

    public int setGenero(int genero){
        return genero;
    }

    public String getSenha(String senha){
        return senha;
    }
    
    public String getSalt(String salt){
        return salt;
    }

    public String getInscrito(String inscrito){
        return inscrito;
    }
    
    public String getUuid(String uuid){
        return uuid;
    }

     @Override
    public void handle(HttpExchange exchange) throws IOException {
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
    }

    int idusuario, String nome, String email, String data_nascimento, int cep, int genero, String senha, String salt, String inscrito, String uuid 

    public void handleGet(HttpExchange exchange) throws IOException{
         StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < comentarios.size(); i++) {
            Comentario x = comentarios.get(i);
            json.append(String.format("{\"ID\": \"%s\", \"Comentario\": \"%s\", \"Nota\": \"%s\", \"Data\":\"%s\", \"ID usuario\":\"%s\", \"ID Receita\":\"%s\"}",
                    x.getID(), x.getNome(), x.getEmail(), x.getData_nascimento(), x.getUsuario(), x.getReceita()));
            if (i < comentarios.size() - 1) json.append(",");
        }
        json.append("]");

        byte[] bytes = json.toString().getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(200, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

     private void handlePost(HttpExchange exchange) throws IOException {
        InputStream is = exchange.getRequestBody();
        String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);

        String idcomentario = body.replaceAll(".*\"Comentario ID\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        String comentarioDescri = body.replaceAll(".*\"Descricao\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        String Nota = body.replaceAll(".*\"Nota\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        String data = body.replaceAll(".*\"DataComentario\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        String idusuario = body.replaceAll(".*\"Usuario ID\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        String idreceita = body.replaceAll(".*\"Receita ID\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        int comen = Integer.parseInt(idcomentario);
        int recei = Integer.parseInt(idreceita);
        int usua = Integer.parseInt(idusuario);
        int nota = Integer.parseInt(Nota);

        Usuario usuarioobj = null;
        for(Usuario usu :  Usuario.usuarios){
            if (usu.getID() == (usua)){
                usuarioobj = usu;
                break;
            }
        }

        Receita receitaobj = null;
        for(Receita rece :  Receita.receitas){
            if (rece.getID() == (recei)){
                receitaobj = rece;
                break;
            }
        }
        
        
        new Comentario(comen, comentarioDescri, nota, data, usuarioobj, receitaobj);

        String response = "{\"message\": \"Categoria adicionada com sucesso\"}";
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(201, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

}
