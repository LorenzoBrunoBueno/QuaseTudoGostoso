
public class CategoriaReceita implements Comparable<CategoriaReceita>{
    int idcategoriareceita;
    Receita receita;
    Categoria categoria;
   
    public CategoriaReceita(int idcategoriareceita, Receita receita, Categoria categoria){
        this.idcategoriareceita = idcategoriareceita;
        this.receita = receita;
        this.categoria = categoria;
        receita.categoriasreceitas.add(this);
        categoria.categoriasreceitas.add(this);
    }

    @Override
    public int compareTo(CategoriaReceita other) {
        // Example: Sort by 'nome' in ascending order
        return (this.receita.compareTo(other.receita));
    }



}
