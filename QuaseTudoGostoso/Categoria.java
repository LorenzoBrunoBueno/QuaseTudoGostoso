import java.util.ArrayList;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;

public class Categoria implements HttpHandler, Comparable<Categoria>{
    private int idcategoria;
    private String categoriaDesc;
    private int ativo;

    public static ArrayList<Categoria> categorias = new ArrayList<>();
    public ArrayList<CategoriaReceita> categoriasreceitas = new ArrayList<>();
   

    public Categoria(int idcategoria, String categoriaDesc, int ativo) throws Exception{
        this.idcategoria = idcategoria;
        this.categoriaDesc = categoriaDesc;
        this.ativo = ativo;

      
    }

    public Categoria(String categoriaDesc, int ativo) throws Exception{
        this.categoriaDesc = categoriaDesc;
        this.ativo = ativo;

      
    }

    public void inserir() throws Exception{
          PreparedStatement stmt = DAO.createConnection().prepareStatement (
            "INSERT INTO categoria (categoria, ativo) VALUES (?, ?);"
        );
        stmt.setString(1, this.getCategoriaDesc());
        stmt.setInt(2, this.getAtivo());

        stmt.execute();
        DAO.closeConnection();
    }

    public static void addCategoria(Categoria categoria){
        Categoria.categorias.add(categoria);
    }

    @Override
    public String toString() {
        return
         "" +
        "Id:" + this.getID() + "\n" +
        "Descrição:" + this.getCategoriaDesc() + "\n" +
        "Ativo:" + this.getAtivo();
    }

     public static ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public Categoria(){
        
    }

    @Override
    public int compareTo(Categoria other) {
        // Example: Sort by 'nome' in ascending order
        return this.categoriaDesc.compareTo(other.categoriaDesc);
    }

     public int getID(){
        return idcategoria;
    }

    public String getCategoriaDesc(){
        return categoriaDesc;
    }

    public int getAtivo(){
        return ativo;
    }

    public void setID(int idcategoria){
        this.idcategoria = idcategoria;
    }

    public void setCategoriaDesc(String categoriaDesc){
        this.categoriaDesc = categoriaDesc;
    }

    public void setAtivo(int ativo){
        this.ativo = ativo;
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
            String response = "Método não suportado";
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

    private void handleGet(HttpExchange exchange) throws IOException {
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < categorias.size(); i++) {
            Categoria c = categorias.get(i);
            json.append(String.format("{\"id\": \"%s\",\"Descricao\": \"%s\",\"ativo\": \"%s\"}",
                    c.getID(), c.getCategoriaDesc(), c.getAtivo()));
            if (i < categorias.size() - 1) json.append(",");
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

        String categoriaDescri = body.replaceAll(".*\"Descricao\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        String Ativo = body.replaceAll(".*\"ativo\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        int ativo = Integer.parseInt(Ativo);
        
        new Categoria(categoriaDescri, ativo);

        String response = "{\"message\": \"Categoria adicionada com sucesso\"}";
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(201, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

}
