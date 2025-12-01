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
        System.out.println("[3] Deletar Usuário");
        System.out.println("[4] Alterar Usuário");

        System.out.println("[5] Listar Categoria");
        System.out.println("[6] Adicionar Categoria");
        System.out.println("[7] Deletar Categoria");
        System.out.println("[8] Alterar Categoria");

        System.out.println("[9] Listar Receitas");
        System.out.println("[10] Adicionar Receita");
        System.out.println("[11] Deletar Receitas");
        System.out.println("[12] Alterar Receita");

        System.out.println("[13] Listar Comentários");
        System.out.println("[14] Adicionar Comentário");
        System.out.println("[15] Deletar Comentários");
        System.out.println("[16] Alterar Comentário");

        System.out.println("[17] Listar CategoriaReceita");
        System.out.println("[18] Adicionar CategoriaReceita");
        System.out.println("[19] Deletar CategoriaReceita");
        System.out.println("[20] Alterar CategoriaReceita");
        System.out.println("[21] Sair");
        do{
            System.out.println("Insira o número correspondente a Opção: ");
            op = scanner.nextLine();
        
        }while(!op.equals("1") && !op.equals("2") && !op.equals("3") && !op.equals("4") && !op.equals("5") && !op.equals("6") && !op.equals("7") && !op.equals("8") && !op.equals("9") && !op.equals("10") && !op.equals("11") &&
        !op.equals("12") && !op.equals("13") && !op.equals("14") && !op.equals("15") && !op.equals("16") && !op.equals("17") && !op.equals("18") && !op.equals("19") && !op.equals("20") && !op.equals("21")
        );
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

                System.out.println("Insira o ID do Usuário que deseja deletar");
                int idusuDel = Integer.parseInt(scanner.nextLine());

                Usuario usuDel = new Usuario(idusuDel);
                usuDel.deletar();
     
                } catch (Exception e){
                    System.out.print("Erro ao deletar Usuário!");   
                    e.printStackTrace();
                }  
                break;
             case "4":
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

                System.out.println("Insira o ID do Usuário que deseja alterar");
                int idusuAlt = Integer.parseInt(scanner.nextLine());


                System.out.print("Digite o novo nome: ");
                String newNome = scanner.nextLine();
                System.out.print("Digite o novo email: ");
                String newEmail = scanner.nextLine();
                System.out.print("Digite a nova data de Nascimento (AAAA-MM-DD): ");
                String newData_nascimento = scanner.nextLine();
                System.out.print("Digite o novo CEP: ");
                int newCep = Integer.parseInt(scanner.nextLine());
                System.out.print("Digite o novo gênero (1 para Homen, 0 para Mulher):");
                int newGenero = Integer.parseInt(scanner.nextLine());
                System.out.print("Digite a senha: ");
                String newSenha = scanner.nextLine();


                Usuario usuAlt = new Usuario(idusuAlt, newNome, newEmail, newData_nascimento, newCep, newGenero, newSenha);
                usuAlt.alterar();
     
                } catch (Exception e){
                    System.out.print("Erro ao alterar Usuário!");   
                    e.printStackTrace();
                }  
                break;
            
             case "5":
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
             case "6":
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

             case "7":
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

                System.out.println("Insira o ID da Categoria que deseja deletar");
                int idcateDel = Integer.parseInt(scanner.nextLine());

                Categoria cateDel = new Categoria(idcateDel);
                cateDel.deletar();
     
                } catch (Exception e){
                    System.out.print("Erro ao deletar Categoria!");   
                    e.printStackTrace();
                }  
                break;

             case "8":
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

                System.out.println("Insira o ID da categoria que deseja alterar");
                int idcateAlt = Integer.parseInt(scanner.nextLine());

                System.out.print("Digite o novo nome da Categoria: ");
                String newCategoria = scanner.nextLine();
                System.out.print("Digite o novo status: Ativo 1 e Desativado 0 ");
                int newAtivo = Integer.parseInt(scanner.nextLine());



                Categoria cateAlt = new Categoria(idcateAlt, newCategoria, newAtivo);
                cateAlt.alterar();
     
                } catch (Exception e){
                    System.out.print("Erro ao alterar Categoria!");   
                    e.printStackTrace();
                }  
                break;

             case "9":
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
             case "10":
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

             case "11":
                try{
                    ResultSet rs = conexao.createStatement().executeQuery(
                    "SELECT * FROM receita;"
                );
                while(rs.next()){
                    Receita receita2 = new Receita(
                        rs.getInt("idreceita"), 
                        rs.getString("titulo"),
                        rs.getString("descricao")

                    );
                    System.out.println(receita2);
                    System.out.println("===================================");
                }

                System.out.println("Insira o ID da Receita que deseja deletar");
                int idreceDel = Integer.parseInt(scanner.nextLine());

                Receita receDel = new Receita(idreceDel);
                receDel.deletar();
     
                } catch (Exception e){
                    System.out.print("Erro ao deletar receita!");   
                    e.printStackTrace();
                } 
                break;

             case "12":
                try{
                    ResultSet rs = conexao.createStatement().executeQuery(
                    "SELECT * FROM receita;"
                );
                while(rs.next()){
                    Receita receita2 = new Receita(
                        rs.getInt("idreceita"), 
                        rs.getString("titulo"),
                        rs.getString("descricao")


                    );
                    System.out.println(receita2);
                    System.out.println("===================================");
                }

                System.out.println("Insira o ID da receita que deseja alterar");
                int idreceAlt = Integer.parseInt(scanner.nextLine());

                System.out.print("Digite o novo titulo da receita: ");
                String newTitulo = scanner.nextLine();
                System.out.print("Digite a nova descricao da receita: ");
                String newDescri = scanner.nextLine();



                Receita receAlt = new Receita(idreceAlt, newTitulo, newDescri);
                receAlt.alterar();
     
                } catch (Exception e){
                    System.out.print("Erro ao alterar a Receita!");   
                    e.printStackTrace();
                }  
                break;

             case "13":
                  try{
                     ResultSet rs = conexao.createStatement().executeQuery(
                    "SELECT c.idcomentario, c.comentario, c.nota, c.datacomentario, r.idreceita, r.titulo, r.descricao, r.imagem, u.idusuario, u.nome, u.email, u.data_nascimento, u.cep, u.genero, u.senha, u.salt, u.inscrito, u.uuid FROM comentario c INNER JOIN receita r ON c.receita_idreceita = r.idreceita INNER JOIN usuario u ON c.usuario_idusuario = u.idusuario;"
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
             case "14":
                try{
                    System.out.print("Digite o Comentário: ");
                    String comentarioDesc = scanner.nextLine();
                    System.out.print("Digite a nota do Comentário: ");
                    int nota = Integer.parseInt(scanner.nextLine());
                    System.out.print("Digite a data do Comentário (2025-11-24 15:30:00): ");
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
                    "SELECT r.idreceita, r.titulo, r.descricao, u.idusuario, u.nome, u.email, u.data_nascimento, u.cep, u.genero, u.senha, u.salt, u.inscrito, u.uuid FROM receita r INNER JOIN usuario u ON r.cadastro_idusuario = u.idusuario"
                        
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
                    "SELECT r.idreceita, r.titulo, r.descricao, u.idusuario, u.nome, u.email, u.data_nascimento, u.cep, u.genero, u.senha, u.salt, u.inscrito, u.uuid FROM receita r INNER JOIN usuario u ON r.cadastro_idusuario = u.idusuario"
                        
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

             case "15":

                 try{
                    ResultSet rs = conexao.createStatement().executeQuery(
                    "SELECT * FROM comentario;"
                );
                while(rs.next()){
                    Comentario comentario2 = new Comentario(
                        rs.getInt("idcomentario"), 
                        rs.getString("comentario"),
                        rs.getInt("nota"),
                        rs.getString("datacomentario")

                    );
                    System.out.println(comentario2);
                    System.out.println("===================================");
                }

                System.out.println("Insira o ID do comentario que deseja deletar");
                int idcomeDel = Integer.parseInt(scanner.nextLine());

                Receita comeDel = new Receita(idcomeDel);
                comeDel.deletar();
     
                } catch (Exception e){
                    System.out.print("Erro ao deletar comentario!");   
                    e.printStackTrace();
                } 

                break;

             case "16":
                try{
                    ResultSet rs = conexao.createStatement().executeQuery(
                    "SELECT * FROM comentario;"
                );
                while(rs.next()){
                    Comentario comentario2 = new Comentario(
                        rs.getInt("idcomentario"), 
                        rs.getString("comentario"),
                        rs.getInt("nota"),
                        rs.getString("datacomentario")

                    );
                    System.out.println(comentario2);
                    System.out.println("===================================");
                }

                System.out.println("Insira o ID do comentario que deseja alterar");
                int idcomeAlt = Integer.parseInt(scanner.nextLine());

                System.out.print("Digite o novo comentario da receita: ");
                String newComentario = scanner.nextLine();
                System.out.print("Digite a nova nota da receita: ");
                int newNota = Integer.parseInt(scanner.nextLine());



                Comentario comeAlt = new Comentario(idcomeAlt, newComentario, newNota);
                comeAlt.alterar();
     
                } catch (Exception e){
                    System.out.print("Erro ao alterar o Comentário!");   
                    e.printStackTrace();
                }  
                break;

             case "17":
                 try{
                     ResultSet rs = conexao.createStatement().executeQuery(
                    "SELECT cr.receita_idreceita, cr.categoria_idcategoria, r.idreceita, r.titulo, r.descricao, c.idcategoria, c.categoria, c.ativo FROM categoria_receita cr INNER JOIN receita r ON cr.receita_idreceita = r.idreceita INNER JOIN categoria c ON cr.categoria_idcategoria = c.idcategoria;"
                );
                while(rs.next()){


                   Categoria categoria2 = new Categoria(
                        rs.getInt("idcategoria"),
                        rs.getString("categoria"),
                        rs.getInt("ativo")

                   );

                    Receita receita2 = new Receita(
                        rs.getInt("idreceita"), 
                        rs.getString("titulo"),
                        rs.getString("descricao")
                
                    );

                    CategoriaReceita categoriaReceita = new CategoriaReceita(
                        receita2,
                        categoria2

                    );

                    System.out.println(categoriaReceita);
                    System.out.println("===================================");
                }
                }catch(Exception e){
                     e.printStackTrace();
                    System.out.println("Erro ao carregar usuários");

                }
                break;
             case "18":
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
                    System.out.println("Erro ao cadastrar categoria_receita");

                }
                break;

             case "19":
                 try{
                    ResultSet rs = conexao.createStatement().executeQuery(
                    "SELECT cr.receita_idreceita, cr.categoria_idcategoria, r.idreceita, r.titulo, r.descricao, c.idcategoria, c.categoria, c.ativo FROM categoria_receita cr INNER JOIN receita r ON cr.receita_idreceita = r.idreceita INNER JOIN categoria c ON cr.categoria_idcategoria = c.idcategoria;"
                    
                );
                while(rs.next()){
                     Categoria categoria2 = new Categoria(
                        rs.getInt("idcategoria"),
                        rs.getString("categoria"),
                        rs.getInt("ativo")

                   );

                    Receita receita2 = new Receita(
                        rs.getInt("idreceita"), 
                        rs.getString("titulo"),
                        rs.getString("descricao")
                
                    );

                    CategoriaReceita categoriaReceita = new CategoriaReceita(
                        receita2,
                        categoria2

                    );
                    System.out.println(categoriaReceita);
                    System.out.println("===================================");
                }

                System.out.println("Insira o ID da Receita:");
                int idReceDel = Integer.parseInt(scanner.nextLine());
                System.out.println("Insira o ID da Categoria:");
                int idCateDel = Integer.parseInt(scanner.nextLine());

                CategoriaReceita rececateDel = new CategoriaReceita(idReceDel,idCateDel);
                rececateDel.deletar();
     
                } catch (Exception e){
                    System.out.print("Erro ao deletar Categoria_Receita!");   
                    e.printStackTrace();
                } 

                break;

             case "20":
                try{
                    ResultSet rs = conexao.createStatement().executeQuery(
                    "SELECT cr.receita_idreceita, cr.categoria_idcategoria, r.idreceita, r.titulo, r.descricao, c.idcategoria, c.categoria, c.ativo FROM categoria_receita cr INNER JOIN receita r ON cr.receita_idreceita = r.idreceita INNER JOIN categoria c ON cr.categoria_idcategoria = c.idcategoria;"
                    
                );
                while(rs.next()){
                     Categoria categoria2 = new Categoria(
                        rs.getInt("idcategoria"),
                        rs.getString("categoria"),
                        rs.getInt("ativo")

                   );

                    Receita receita2 = new Receita(
                        rs.getInt("idreceita"), 
                        rs.getString("titulo"),
                        rs.getString("descricao")
                
                    );

                    CategoriaReceita categoriaReceita = new CategoriaReceita(
                        receita2,
                        categoria2

                    );
                    System.out.println(categoriaReceita);
                    System.out.println("===================================");
                }

                System.out.println("Insira o ID da Receita:");
                int idReceDel = Integer.parseInt(scanner.nextLine());
                System.out.println("Insira o ID da Categoria:");
                int idCateDel = Integer.parseInt(scanner.nextLine());

                System.out.println("Insira o novo ID da Receita:");
                int newidReceDel = Integer.parseInt(scanner.nextLine());
                System.out.println("Insira o novo ID da Categoria:");
                int newidCateDel = Integer.parseInt(scanner.nextLine());

                CategoriaReceita rececateDel = new CategoriaReceita(idReceDel,idCateDel);
                rececateDel.deletar();

                CategoriaReceita rececateIns = new CategoriaReceita(newidReceDel,newidCateDel);
                rececateIns.inserir();
     
                } catch (Exception e){
                    System.out.print("Erro ao alterar Categoria_Receita!");   
                    e.printStackTrace();
                } 

                break;
            }
        }while(!op.equals( "21"));
        scanner.close();
    }
}
