public class Categoria implements Comparable<Categoria>{
    int idcategoria;
    String categoriaDesc;
    int ativo;
   
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

}
