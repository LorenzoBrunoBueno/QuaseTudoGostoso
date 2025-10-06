
public class CategoriaReceita implements Comparable<CategoriaReceita>{
    int receita_idreceita;
    int categoria_idcategoria;
   
    public CategoriaReceita(int receita_idreceita, int categoria_idcategoria){
        this.receita_idreceita = receita_idreceita;
        this.categoria_idcategoria = categoria_idcategoria;
    }

    @Override
    public int compareTo(CategoriaReceita other) {
        // Example: Sort by 'nome' in ascending order
        return Integer.compare(this.receita_idreceita, other.receita_idreceita);
    }

}
