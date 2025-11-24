import java.util.Scanner;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.ResultSet;


public class QTGostoso {
    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        /*SortedSet<Usuario> usuarios = new TreeSet<>();
        SortedSet<Categoria> categorias = new TreeSet<>();
        SortedSet<Comentario> comentarios = new TreeSet<>();
        SortedSet<Receita> receitas = new TreeSet<>();
        SortedSet<CategoriaReceita> categoriareceita = new TreeSet<>();*/
        /*Usuario usuarios = new Usuario();
        /*Categoria categorias = new Categoria();
        Comentario comentarios = new Comentario();
        Receita receitas = new Receita();
        CategoriaReceita categoriareceitas = new CategoriaReceita();*/
        HttpServer server = HttpServer.create(new InetSocketAddress(8888), 0);

        server.createContext("/receitas", new Receita());
        server.createContext("/comentarios", new Comentario());
        server.createContext("/categorias", new Categoria());
        server.createContext("/usuarios", new Usuario());
        server.createContext("/categoriasreceitas", new CategoriaReceita());
        Connection conexao = DAO.createConnection();


        server.setExecutor(null);
        server.start();
        System.out.println("Servidor rodando em http://localhost:8888/");


        String op;
        do{
        System.out.println("\n\n ===Quase Tudo Gostoso===");
        System.out.println("[1] Listar Usuários");
        System.out.println("[2] Adicionar Usuário");
        System.out.println("[3] Listar Categoria");
        System.out.println("[4] Adicionar Categoria");
        System.out.println("[5] Listar Receitas");
        System.out.println("[6] Adicionar Receita");
        System.out.println("[7] Listar Comentários");
        System.out.println("[8] Adicionar Comentário");
        System.out.println("[9] Listar CategoriaReceita");
        System.out.println("[10] Adicionar CategoriaReceita");
        System.out.println("[11] Sair");
        do{
            System.out.println("Insira o número correspondente a Opção: ");
            op = scanner.nextLine();
        
        }while(!op.equals("1") && !op.equals("2") && !op.equals("3") && !op.equals("4") && !op.equals("5") && !op.equals("6") && !op.equals("7") && !op.equals("8") && !op.equals("9") && !op.equals("10") && !op.equals("11"));
        switch (op) {
            case "1":
                try{
                     ResultSet rs = conexao.createStatement().executeQuery(
                    "SELECT * FROM usuario;"
                );
                while(rs.next()){
                    Usuario usuario2 = new Usuario(
                        rs.getInt("idusuario"), 
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("data_nascimento"),
                        rs.getInt("cep"), 
                        rs.getInt("genero"),
                        rs.getString("senha"),
                        rs.getString("salt"),
                        rs.getString("inscrito"),
                        rs.getString("uuid")

                    );
                    System.out.println(usuario2);
                    System.out.println("===================================");
                }
                }catch(Exception e){
                     e.printStackTrace();
                    System.out.println("Erro ao carregar usuários");

                }
               
                
                break;
             case "2":
                try{
                    System.out.print("Digite o nome do Usuário: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o email: ");
                    String email = scanner.nextLine();
                    System.out.print("Digite a data de Nascimento (AAAA-MM-DD): ");
                    String data_nascimento = scanner.nextLine();
                    System.out.print("Digite o CEP: ");
                    int cep = Integer.parseInt(scanner.nextLine());
                    System.out.print("Digite o gênero (1 para Homen, 0 para Mulher):");
                    int genero = Integer.parseInt(scanner.nextLine());
                    System.out.print("Digite a senha: ");
                    String senha = scanner.nextLine();
                    System.out.print("Digite o Salt: ");
                    String salt = scanner.nextLine();
                    System.out.print("Digite a data de Inscricao (2025-11-24 15:30:00): ");
                    String inscrito = scanner.nextLine();
                    System.out.print("Digite o uuid: ");
                    String uuid = scanner.nextLine();
                    
                    Usuario b = new Usuario(nome, email, data_nascimento, cep, genero, senha, salt, inscrito, uuid);
                    b.inserir();
                    Usuario.addUsuario(b);
                    //usuarios.add(b);
                } catch (Exception e){
                    System.out.print("Erro ao criar usuário!");   
                    e.printStackTrace();
                }  
                   
                break;
             case "3":
                try{
                     ResultSet rs = conexao.createStatement().executeQuery(
                    "SELECT * FROM categoria;"
                );
                while(rs.next()){
                    Categoria categoria2 = new Categoria(
                        rs.getInt("idcategoria"), 
                        rs.getString("categoria"),
                        rs.getInt("ativo")
                    );
                    System.out.println(categoria2);
                    System.out.println("===================================");
                }
                }catch(Exception e){
                     e.printStackTrace();
                    System.out.println("Erro ao carregar categorias");

                }
                break;
             case "4":
                try{
                    System.out.print("Digite o nome da Categoria: ");
                    String categoriaDesc = scanner.nextLine();
                    System.out.print("Digite o estado: 1 para ativo, 2 para não ativo ");
                    int ativo = Integer.parseInt(scanner.nextLine());
                    Categoria a = new Categoria(categoriaDesc, ativo);
                    a.inserir();
                    Categoria.addCategoria(a);
                }catch(Exception e){
                     e.printStackTrace();
                    System.out.print("Erro ao criar categoria");
                }
                    
                break;
             case "5":
                try{
                     ResultSet rs = conexao.createStatement().executeQuery(
                    "SELECT r.idreceita, r.descricao, r.titulo, u.idusuario, u.nome, u.email, u.data_nascimento, u.cep, u.genero, u.senha, u.salt, u.inscrito, u.uuid FROM receita r INNER JOIN usuario u ON r.cadastro_idusuario = u.idusuario"
                );
                while(rs.next()){

                    Usuario receUsuario = new Usuario(
                        rs.getInt("idusuario"), 
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("data_nascimento"),
                        rs.getInt("cep"), 
                        rs.getInt("genero"),
                        rs.getString("senha"),
                        rs.getString("salt"),
                        rs.getString("inscrito"),
                        rs.getString("uuid")

                    );

                    Receita receita2 = new Receita(
                        rs.getInt("idreceita"), 
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        receUsuario
                        
                        
                    );
                    System.out.println(receita2);
                    System.out.println("===================================");
                }
                }catch(Exception e){
                     e.printStackTrace();
                    System.out.println("Erro ao carregar usuários");

                }
                break;
             case "6":
                try{
                    System.out.print("Digite o Titulo: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Digite a Descrição: ");
                    String descricao = scanner.nextLine();
                    System.out.println("Insira o id do usuario autor das opções abaixo: ");
                    
                    try{
                        ResultSet rs = conexao.createStatement().executeQuery(
                        "SELECT * FROM usuario;"
                        );
                        while(rs.next()){
                            Usuario usuario2 = new Usuario(
                                rs.getInt("idusuario"), 
                                rs.getString("nome"),
                                rs.getString("email"),
                                rs.getString("data_nascimento"),
                                rs.getInt("cep"), 
                                rs.getInt("genero"),
                                rs.getString("senha"),
                                rs.getString("salt"),
                                rs.getString("inscrito"),
                                rs.getString("uuid")

                            );
                            System.out.println(usuario2);
                            System.out.println("===================================");
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                        System.out.println("Erro ao carregar usuários");

                    }


                    int autor = Integer.parseInt(scanner.nextLine());
                    Usuario usu;
                     try{
                        ResultSet rs = conexao.createStatement().executeQuery(
                        "SELECT * FROM usuario;"
                        );
                        while(rs.next()){
                            Usuario usuario2 = new Usuario(
                                rs.getInt("idusuario"), 
                                rs.getString("nome"),
                                rs.getString("email"),
                                rs.getString("data_nascimento"),
                                rs.getInt("cep"), 
                                rs.getInt("genero"),
                                rs.getString("senha"),
                                rs.getString("salt"),
                                rs.getString("inscrito"),
                                rs.getString("uuid")

                            );
                           if (usuario2.getID() == autor){
                            usu = usuario2;
                            Receita d = new Receita(titulo, descricao, usu);
                            d.inserir();
                            Receita.addReceita(d);
                           }
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                        System.out.println("Erro ao carregar usuários");

                    }


                }catch(Exception e){
                     e.printStackTrace();
                     System.out.print("Erro ao criar receita");

                }
                break;
             case "7":
                  try{
                     ResultSet rs = conexao.createStatement().executeQuery(
                    "SELECT c.idcomentario, c.comentario AS texto_comentario, c.nota, c.datacomentario, r.idreceita, r.titulo AS receita_titulo, r.descricao AS receita_descricao, r.imagem, u.idusuario AS usuario_id, u.nome AS usuario_nome, u.email AS usuario_email, u.uuid AS usuario_uuid FROM comentario c INNER JOIN receita r ON c.receita_idreceita = r.idreceita INNER JOIN usuario u ON c.usuario_idusuario = u.idusuario;"
                );
                while(rs.next()){


                    Usuario comeUsuario = new Usuario(
                        rs.getInt("idusuario"), 
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("data_nascimento"),
                        rs.getInt("cep"), 
                        rs.getInt("genero"),
                        rs.getString("senha"),
                        rs.getString("salt"),
                        rs.getString("inscrito"),
                        rs.getString("uuid")

                    );

                    Receita comeReceita = new Receita(
                        rs.getInt("idreceita"), 
                        rs.getString("titulo"),
                        rs.getString("descricao")
                
                    );

                    Comentario comentario2 = new Comentario(
                        rs.getInt("idcomentario"), 
                        rs.getString("comentario"),
                        rs.getInt("nota"),
                        rs.getString("datacomentario"),
                        comeUsuario,
                        comeReceita

                    );
                    System.out.println(comentario2);
                    System.out.println("===================================");
                }
                }catch(Exception e){
                     e.printStackTrace();
                    System.out.println("Erro ao carregar usuários");

                }
                break;
             case "8":
                try{
                    System.out.print("Digite o Comentário: ");
                    String comentarioDesc = scanner.nextLine();
                    System.out.print("Digite a nota do Comentário: ");
                    int nota = Integer.parseInt(scanner.nextLine());
                    System.out.print("Digite a data do Comentário: ");
                    String datacomentario = scanner.nextLine();

                    System.out.println("Insira o id do usuario autor das opções abaixo: ");

                    try{
                        ResultSet rs = conexao.createStatement().executeQuery(
                        "SELECT * FROM usuario;"
                        );
                        while(rs.next()){
                            Usuario usuario2 = new Usuario(
                                rs.getInt("idusuario"), 
                                rs.getString("nome"),
                                rs.getString("email"),
                                rs.getString("data_nascimento"),
                                rs.getInt("cep"), 
                                rs.getInt("genero"),
                                rs.getString("senha"),
                                rs.getString("salt"),
                                rs.getString("inscrito"),
                                rs.getString("uuid")

                            );
                            System.out.println(usuario2);
                            System.out.println("===================================");
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                        System.out.println("Erro ao carregar usuários");

                    }

                    int autor = Integer.parseInt(scanner.nextLine());
                    Usuario usu = null;
                    Receita rece = null;

                   try{
                        ResultSet rs = conexao.createStatement().executeQuery(
                        "SELECT * FROM usuario;"
                        );
                        while(rs.next()){
                            Usuario usuario2 = new Usuario(
                                rs.getInt("idusuario"), 
                                rs.getString("nome"),
                                rs.getString("email"),
                                rs.getString("data_nascimento"),
                                rs.getInt("cep"), 
                                rs.getInt("genero"),
                                rs.getString("senha"),
                                rs.getString("salt"),
                                rs.getString("inscrito"),
                                rs.getString("uuid")

                            );
                           if (usuario2.getID() == autor){
                            usu = usuario2;
                           }
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                        System.out.println("Erro ao carregar usuários");

                    }





                    System.out.println("Insira o id da receita alvo do comentario das opções abaixo: ");

                    try{
                        ResultSet rs = conexao.createStatement().executeQuery(
                    "SELECT r.idreceita, r.titulo, u.idusuario, u.nome, u.email, u.data_nascimento, u.cep, u.genero, u.senha, u.salt, u.inscrito, u.uuid FROM receita r INNER JOIN usuario u ON r.usuario_idusuario = u.idusuario"
                        
                        );
                        while(rs.next()){

                            Usuario usuario2 = new Usuario(
                                rs.getInt("idusuario"), 
                                rs.getString("nome"),
                                rs.getString("email"),
                                rs.getString("data_nascimento"),
                                rs.getInt("cep"), 
                                rs.getInt("genero"),
                                rs.getString("senha"),
                                rs.getString("salt"),
                                rs.getString("inscrito"),
                                rs.getString("uuid")

                            );

                            Receita receita2 = new Receita(
                                rs.getInt("idreceita"), 
                                rs.getString("titulo"),
                                rs.getString("descricao"),
                                usuario2
                            );

                            System.out.println(receita2);
                            System.out.println("===================================");
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                        System.out.println("Erro ao carregar usuários");

                    } 
                   
                    int autorComen = Integer.parseInt(scanner.nextLine());
                    
                    try{
                        ResultSet rs = conexao.createStatement().executeQuery(
                    "SELECT r.idreceita, r.titulo, u.idusuario, u.nome, u.email, u.data_nascimento, u.cep, u.genero, u.senha, u.salt, u.inscrito, u.uuid FROM receita r INNER JOIN usuario u ON r.usuario_idusuario = u.idusuario"
                        
                        );
                        while(rs.next()){

                            Usuario usuario2 = new Usuario(
                                rs.getInt("idusuario"), 
                                rs.getString("nome"),
                                rs.getString("email"),
                                rs.getString("data_nascimento"),
                                rs.getInt("cep"), 
                                rs.getInt("genero"),
                                rs.getString("senha"),
                                rs.getString("salt"),
                                rs.getString("inscrito"),
                                rs.getString("uuid")

                            );

                            Receita receita2 = new Receita(
                                rs.getInt("idreceita"), 
                                rs.getString("titulo"),
                                rs.getString("descricao"),
                                usuario2
                            );
                            if (receita2.getID() == autorComen){
                                rece = receita2;
                            }
                            
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                        System.out.println("Erro ao carregar usuários");

                    } 

                    if (usu != null && rece != null){
                        Comentario c = new Comentario(comentarioDesc, nota, datacomentario, usu, rece);
                        c.inserir();
                        Comentario.addComentario(c);
                    }
                   
                }catch(Exception e){
                     e.printStackTrace();
                    System.out.print("Erro ao criar comentario");
                }
                    
                break;
             case "9":
                 try{
                     ResultSet rs = conexao.createStatement().executeQuery(
                    "SELECT c.idcomentario, c.comentario AS texto_comentario, c.nota, c.datacomentario, r.idreceita, r.titulo AS receita_titulo, r.descricao AS receita_descricao, r.imagem, u.idusuario AS usuario_id, u.nome AS usuario_nome, u.email AS usuario_email, u.uuid AS usuario_uuid FROM comentario c INNER JOIN receita r ON c.receita_idreceita = r.idreceita INNER JOIN usuario u ON c.usuario_idusuario = u.idusuario;"
                );
                while(rs.next()){


                    Usuario comeUsuario = new Usuario(
                        rs.getInt("idusuario"), 
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("data_nascimento"),
                        rs.getInt("cep"), 
                        rs.getInt("genero"),
                        rs.getString("senha"),
                        rs.getString("salt"),
                        rs.getString("inscrito"),
                        rs.getString("uuid")

                    );

                    Receita comeReceita = new Receita(
                        rs.getInt("idreceita"), 
                        rs.getString("titulo"),
                        rs.getString("descricao")
                
                    );

                    Comentario comentario2 = new Comentario(
                        rs.getInt("idcomentario"), 
                        rs.getString("comentario"),
                        rs.getInt("nota"),
                        rs.getString("datacomentario"),
                        comeUsuario,
                        comeReceita

                    );
                    System.out.println(comentario2);
                    System.out.println("===================================");
                }
                }catch(Exception e){
                     e.printStackTrace();
                    System.out.println("Erro ao carregar usuários");

                }
                break;
             case "10":
                try{
                    System.out.println("Insira o id da receita das opções abaixo: ");
                    for (Receita receita : Receita.receitas){
                        System.out.println("ID: "+receita.getID()+", Titulo da Receita: "+receita.getTitulo());
                    }
                    int receita_idreceita = Integer.parseInt(scanner.nextLine());
                    Receita rece = Receita.receitas.stream()
                        .filter(u -> u.getID() == receita_idreceita)
                        .findFirst()
                        .orElse(null);
                    System.out.println("Insira o id da categoria das opções abaixo: ");
                    for (Receita receita : Receita.receitas){
                        System.out.println("ID: "+receita.getID()+", Titulo da Receita: "+receita.getTitulo());
                    }
                    int categoria_idcategoria = Integer.parseInt(scanner.nextLine());
                    Categoria cate = Categoria.categorias.stream()
                        .filter(u -> u.getID() == categoria_idcategoria)
                        .findFirst()
                        .orElse(null);
                    CategoriaReceita e = new CategoriaReceita(rece, cate);
                    CategoriaReceita.addCategoriaReceita(e);
                }catch(Exception e){
                     e.printStackTrace();
                    System.out.println("Erro ao criar categoria_receita");

                }
                break;
            }
        }while(!op.equals( "11"));
        scanner.close();
    }
}
