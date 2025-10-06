import java.util.ArrayList;

public class Usuario implements Comparable<Usuario> {

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

    public Usuario(String nome, String email, String data_nascimento, int cep, int genero, String senha, String salt, String inscrito, String uuid ){

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
        return this.nome.compareTo(other.nome);
    }

}
