import java.util.ArrayList;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;


public class CategoriaReceita implements HttpHandler, Comparable<CategoriaReceita>{
    int idcategoriareceita;
    Receita receita;
    Categoria categoria;

    public static ArrayList<CategoriaReceita> categoriasreceitas = new ArrayList<>();
   
    public CategoriaReceita(Receita receita, Categoria categoria) throws Exception{
        this.receita = receita;
        this.categoria = categoria;

        

        receita.categoriasreceitas.add(this);
        categoria.categoriasreceitas.add(this);
    }

    public CategoriaReceita(int idcategoriareceita, Receita receita, Categoria categoria) throws Exception{
        this.idcategoriareceita = idcategoriareceita;
        this.receita = receita;
        this.categoria = categoria;

        

        receita.categoriasreceitas.add(this);
        categoria.categoriasreceitas.add(this);
    }

    public void inserir() throws Exception{
        PreparedStatement stmt = DAO.createConnection().prepareStatement (
            "INSERT INTO categoria_receita (categoria_idcategoria, receita_idreceita) VALUES (?, ?);"
        );
        stmt.setInt(1, this.categoria.getID());
        stmt.setInt(2, this.receita.getID());

        stmt.execute();
        DAO.closeConnection();
    }

    @Override
    public int compareTo(CategoriaReceita other) {
        // Example: Sort by 'nome' in ascending order
        return (this.receita.compareTo(other.receita));
    }

    public CategoriaReceita(){
        
    }

    public int getID(){
        return idcategoriareceita;
    }

    public Receita getReceita(){
        return receita;
    }

    public Categoria getCategoria(){
        return categoria;
    }

    public void setID(int idcategoriareceita){
        this.idcategoriareceita = idcategoriareceita;
    }

    public void setCategoria(Categoria categoria){
        this.categoria = categoria;
    }


    public void setReceita(Receita receita){
        this.receita = receita;
    }

     public static void addCategoriaReceita(CategoriaReceita categoriaReceita){
        CategoriaReceita.categoriasreceitas.add(categoriaReceita);
    }

    @Override
    public String toString() {
        return
         "" +
        "Id:" + this.getID() + "\n" +
        "Receita:" + this.getReceita() + "\n" +
        "Categoria:" + this.getCategoria();
    }

    
    public static ArrayList<CategoriaReceita> getCategoriaReceitas() {
        return categoriasreceitas;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
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
        } catch (Exception e) {

        }

       
    }

    private void handleGet(HttpExchange exchange) throws IOException {
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < categoriasreceitas.size(); i++) {
            CategoriaReceita c = categoriasreceitas.get(i);
            json.append(String.format("{\"id\": \"%s\",\"Receita ID\": \"%s\",\"Categoria ID\": \"%s\"}",
                    c.getID(), c.getReceita(), c.getCategoria()));
            if (i < categoriasreceitas.size() - 1) json.append(",");
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

        String receita = body.replaceAll(".*\"Receita ID\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        String categoira = body.replaceAll(".*\"Categoria Id\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        int catego = Integer.parseInt(categoira);
        int recei = Integer.parseInt(receita);
        Categoria categoriaobj = null;
        for(Categoria cate :  Categoria.categorias){
            if (cate.getID() == (catego)){
                categoriaobj = cate;
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
        
        new CategoriaReceita(receitaobj, categoriaobj);

        String response = "{\"message\": \"Categoria adicionada com sucesso\"}";
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(201, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

}
