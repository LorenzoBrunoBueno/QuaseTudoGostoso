import java.util.ArrayList;

public class Categoria implements Comparable<Categoria>{
    int idcategoria;
    String categoriaDesc;
    int ativo;

    ArrayList<CategoriaReceita> categoriasreceitas = new ArrayList<>();
   
    public Categoria(int idcategoria, String categoriaDesc, int ativo){
        this.idcategoria = idcategoria;
        this.categoriaDesc = categoriaDesc;
        this.ativo = ativo;
    }

    @Override
    public int compareTo(Categoria other) {
        // Example: Sort by 'nome' in ascending order
        return this.categoriaDesc.compareTo(other.categoriaDesc);
    }

     public int getID(){
        return idcategoria;
    }
}
