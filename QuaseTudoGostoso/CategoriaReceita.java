
public class CategoriaReceita implements Comparable<CategoriaReceita>{
    Receita receita;
    Categoria categoria;
   
    public CategoriaReceita(Receita receita, Categoria categoria){
        this.receita = receita;
        this.categoria = categoria;
    }

    @Override
    public int compareTo(CategoriaReceita other) {
        // Example: Sort by 'nome' in ascending order
        return (this.receita.compareTo(other.receita));
    }



}
