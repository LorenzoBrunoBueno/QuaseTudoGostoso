import java.util.ArrayList;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Comentario implements HttpHandler, Comparable<Comentario>{
    int idcomentario;
    String comentarioDesc;
    int nota;
    String datacomentario;
    Usuario usuario;
    Receita receita;

    public static ArrayList<Comentario> comentarios = new ArrayList<>();

    public Comentario(int idcomentario, String comentarioDesc, int nota, String datacomentario, Usuario usuario, Receita receita){
        this.idcomentario = idcomentario;
        this.comentarioDesc = comentarioDesc;
        this.nota = nota;
        this.datacomentario = datacomentario;
        this.usuario = usuario;
        this.receita = receita;
        receita.comentarios.add(this);
        usuario.comentarios.add(this);
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
        "Nome:" + this.getComentarioDesc() + "\n" +
        "Email:" + this.getNota() + "\n" +
        "Data Nascimento:" + this.getDatacomentario() + "\n" +
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
