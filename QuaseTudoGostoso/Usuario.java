import java.util.ArrayList;

public class Usuario implements Comparable<Usuario> {
        int idusuario;
        String nome;
        String email;
        String data_nascimento;
        int cep;
        int genero;
        String senha;
        String salt;
        String inscrito;
        String uuid;

        ArrayList<Comentario> comentarios = new ArrayList<>();
        ArrayList<Receita> receitas = new ArrayList<>();

    public Usuario(int idusuario, String nome, String email, String data_nascimento, int cep, int genero, String senha, String salt, String inscrito, String uuid ){
        this.idusuario = idusuario;
        this.nome = nome;
        this.email = email;
        this.data_nascimento = data_nascimento;
        this.cep = cep;
        this.genero = genero;
        this.senha = senha;
        this.salt = salt;
        this.inscrito = inscrito;
        this.uuid = uuid;
    }

    @Override
    public int compareTo(Usuario other) {
        // Example: Sort by 'nome' in ascending order
        return Integer.compare(idusuario, other.idusuario);
    }

    public int getID(){
        return idusuario;
    }

    /*public ArrayList<Receita> getReceitas(){
        return receitas;
    }*/

}
