import java.util.ArrayList;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;

public class Comentario implements HttpHandler, Comparable<Comentario>{
    int idcomentario;
    String comentarioDesc;
    int nota;
    String datacomentario;
    Usuario usuario;
    Receita receita;

    public static ArrayList<Comentario> comentarios = new ArrayList<>();

    public Comentario(String comentarioDesc, int nota, String datacomentario, Usuario usuario, Receita receita) throws Exception{
        this.comentarioDesc = comentarioDesc;
        this.nota = nota;
        this.datacomentario = datacomentario;
        this.usuario = usuario;
        this.receita = receita;

       


        receita.comentarios.add(this);
        usuario.comentarios.add(this);
    }

    public Comentario(int idcomentario) throws Exception{
        this.idcomentario = idcomentario;
    }

    public Comentario(int idcomentario, String comentarioDesc, int nota) throws Exception{
        this.idcomentario = idcomentario;
        this.comentarioDesc = comentarioDesc;
        this.nota = nota;
    }

    public Comentario(int idcomentario, String comentarioDesc, int nota, String datacomentario) throws Exception{
        this.idcomentario = idcomentario;
        this.comentarioDesc = comentarioDesc;
        this.nota = nota;
        this.datacomentario = datacomentario;
    }

    public Comentario(int idcomentario, String comentarioDesc, int nota, String datacomentario, Usuario usuario, Receita receita) throws Exception{
        this.idcomentario = idcomentario;
        this.comentarioDesc = comentarioDesc;
        this.nota = nota;
        this.datacomentario = datacomentario;
        this.usuario = usuario;
        this.receita = receita;

       


        receita.comentarios.add(this);
        usuario.comentarios.add(this);
    }


    public void inserir()throws Exception{
         PreparedStatement stmt = DAO.createConnection().prepareStatement (
            "INSERT INTO comentario (comentario, nota, datacomentario, usuario_idusuario, receita_idreceita) VALUES (?, ?, ?, ?, ?);"
        );
        stmt.setString(1, this.getComentarioDesc());
        stmt.setInt(2, this.getNota());
        stmt.setString(3, this.getDatacomentario());
        stmt.setInt(4, this.usuario.getID());
        stmt.setInt(5, this.receita.getID());

        stmt.execute();
        DAO.closeConnection();
    }

    public void alterar() throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement(
            "UPDATE comentario SET comentario = ?, nota = ? WHERE idcomentario = ?"
        );

        stmt.setString(1, this.getComentarioDesc());
        stmt.setInt(2, this.getNota());
        stmt.setInt(3, this.getID());

        stmt.executeUpdate();
        DAO.closeConnection();
    }

    public void deletar() throws Exception{
        PreparedStatement stmt = DAO.createConnection().prepareStatement("DELETE FROM comentario where idcomentario = ?");

        stmt.setInt(1, this.getID());
        stmt.executeUpdate();
        DAO.closeConnection();
    }


    @Override
    public int compareTo(Comentario other) {
        // Example: Sort by 'nome' in ascending order
        return this.datacomentario.compareTo(other.datacomentario);
    }

    public int getID(){
        return idcomentario;
    }

    public String getComentarioDesc(){
        return comentarioDesc;
    }

    public int getNota(){
        return nota;
    }

    public String getDatacomentario(){
        return datacomentario;
    }

    public Usuario getUsuario(){
        return usuario;
    }
    public Receita getReceita(){
        return receita;
    }

    public void setID(int idcomentario){
        this.idcomentario = idcomentario;
    }

    public void setComentarioDesc(String comentarioDesc){
        this.comentarioDesc = comentarioDesc;
    }

    public void setNota(int nota){
        this.nota = nota;
    }

    public void setDatacomentario(String datacomentario){
        this.datacomentario = datacomentario;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    public void setReceita(Receita receita){
        this.receita = receita;
    }

     public static void addComentario(Comentario comentario){
        Comentario.comentarios.add(comentario);
    }

    @Override
    public String toString() {
        return
         "" +
        "Id:" + this.getID() + "\n" +
        "Comentário:" + this.getComentarioDesc() + "\n" +
        "Nota:" + this.getNota() + "\n" +
        "Data do Comentário:" + this.getDatacomentario() + "\n" +
        "Usuario:" + this.getUsuario() + "\n" +
        "Receita:" + this.getReceita();
    }

    
    public static ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

     public Comentario(){
        
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

    public void handleGet(HttpExchange exchange) throws IOException{
         StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < comentarios.size(); i++) {
            Comentario x = comentarios.get(i);
            json.append(String.format("{\"ID\": \"%s\", \"Comentario\": \"%s\", \"Nota\": \"%s\", \"Data\":\"%s\", \"ID usuario\":\"%s\", \"ID Receita\":\"%s\"}",
                    x.getID(), x.getComentarioDesc(), x.getNota(), x.getDatacomentario(), x.getUsuario(), x.getReceita()));
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

     private void handlePost(HttpExchange exchange) throws IOException, Exception {
        InputStream is = exchange.getRequestBody();
        String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);

        String comentarioDescri = body.replaceAll(".*\"Descricao\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        String Nota = body.replaceAll(".*\"Nota\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        String data = body.replaceAll(".*\"DataComentario\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        String idusuario = body.replaceAll(".*\"Usuario ID\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        String idreceita = body.replaceAll(".*\"Receita ID\"\\s*:\\s*\"([^\"]+)\".*", "$1");
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
        
        
        new Comentario(comentarioDescri, nota, data, usuarioobj, receitaobj);

        String response = "{\"message\": \"Categoria adicionada com sucesso\"}";
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(201, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

}
