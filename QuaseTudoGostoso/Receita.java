import java.util.ArrayList;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;

public class Receita implements HttpHandler, Comparable<Receita>{
    private int idreceita;
    private String titulo;
    private String descricao;
    private Usuario usuario;

     public static ArrayList<Receita> receitas = new ArrayList<>();
     public ArrayList<Comentario> comentarios = new ArrayList<>();
     public ArrayList<CategoriaReceita> categoriasreceitas = new ArrayList<>();

    public Receita(String titulo, String descricao, Usuario usuario) throws Exception{
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;

        //usuario.receitas.add(this);
        if (usuario != null) {
            usuario.receitas.add(this);
        }
    }

    public Receita(int idreceita) throws Exception{
        this.idreceita = idreceita;
    }

    public Receita(int idreceita, String titulo, String descricao, Usuario usuario) throws Exception{
        this.idreceita = idreceita;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;

        //usuario.receitas.add(this);
        if (usuario != null) {
            usuario.receitas.add(this);
        }
    }

     public Receita(int idreceita, String titulo, String descricao) throws Exception{
        this.idreceita = idreceita;
        this.titulo = titulo;
        this.descricao = descricao;

        if (usuario != null) {
            usuario.receitas.add(this);
        }
        //usuario.receitas.add(this);
    }

    public void inserir() throws Exception{
         PreparedStatement stmt = DAO.createConnection().prepareStatement (
            "INSERT INTO receita (titulo, descricao, cadastro_idusuario, preparo_idpreparo, dificuldade_iddificuldade, custo_idcusto) VALUES (?, ?, ?, ?, ?, ?);"
        );
        stmt.setString(1, this.getTitulo());
        stmt.setString(2, this.getDescricao());
        stmt.setInt(3, this.usuario.getID());
        stmt.setInt(4, 5);
        stmt.setInt(5, 1);
        stmt.setInt(6, 1);


        stmt.execute();
        DAO.closeConnection();
    }

    public void alterar() throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement(
            "UPDATE receita SET titulo = ?, descricao = ? WHERE idreceita = ?"
        );

        stmt.setString(1, this.getTitulo());
        stmt.setString(2, this.getDescricao());
        stmt.setInt(3, this.getID());

        stmt.executeUpdate();
        DAO.closeConnection();
    }

    public void deletar() throws Exception{
        PreparedStatement stmt = DAO.createConnection().prepareStatement("DELETE FROM receita where idreceita = ?");

        stmt.setInt(1, this.getID());
        stmt.executeUpdate();
        DAO.closeConnection();
    }

    @Override
    public int compareTo(Receita other) {
        // Example: Sort by 'nome' in ascending order
        return this.titulo.compareTo(other.titulo);
    }

    public int getID(){
        return idreceita;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getDescricao(){
        return descricao;
    }

    /*public String getImagem(){
        return imagem;
    }*/

    public Usuario getUsuario(){
        return usuario;
    }

      public void setID(int idreceita){
        this.idreceita = idreceita;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

   /* public void setImagem(String imagem){
        this.imagem = imagem;
    }*/

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

      public static void addReceita(Receita receita){
        Receita.receitas.add(receita);
    }

    @Override
    public String toString() {
        return
         "" +
        "Id:" + this.getID() + "\n" +
        "Titulo:" + this.getTitulo() + "\n" +
        "Descrição:" + this.getDescricao() + "\n";
        /*"Imagem:" + this.getImagem();*/
    }

    public static ArrayList<Receita> getReceitas() {
        return receitas;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
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
        }catch(Exception e){
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

    public Receita(){
        
    }
    
    public void handleGet(HttpExchange exchange) throws IOException{
         StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < receitas.size(); i++) {
            Receita r = receitas.get(i);
            json.append(String.format("{\"ID\": \"%s\", \"Titulo\": \"%s\", \"Descricao\": \"%s\", \"Imagem\":\"%s\", \"Usuario\":\"%s\"}",
                    r.getID(), r.getTitulo(), r.getDescricao(), r.getUsuario()));
            if (i < receitas.size() - 1) json.append(",");
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

        String titulo = body.replaceAll(".*\"Titulo\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        String descricao = body.replaceAll(".*\"Descricao\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        //String imagem = body.replaceAll(".*\"imagem\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        String usuario = body.replaceAll(".*\"Usuario ID\"\\s*:\\s*\"([^\"]+)\".*", "$1");

        int idusuario = Integer.parseInt(usuario);


        Usuario usuarioobj = null;
        for(Usuario usu :  Usuario.usuarios){
            if (usu.getID() == (idusuario)){
                usuarioobj = usu;
                break;
            }
        }

        new Receita(titulo, descricao, usuarioobj);

        String response = "{\"message\": \"Receita adicionada com sucesso\"}";
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(201, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
    }

    }
}