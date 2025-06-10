import java.util.Scanner;

public class controleDeEstoque {
    public static Scanner input = new Scanner(System.in);
    public static final int TAM = 100;

    public static void main(String[] args) {
        Produto[] estoque = new Produto[TAM];

        int qtdProdutos = 0;
        int escolha;

        do {
            System.out.println("\n - MENU DE ESTOQUE -");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Filtrar por categoria");
            System.out.println("4 - Ordenar ");
            System.out.println("5 - Remover produto");
            System.out.println("6 - Atualizar preço");
            System.out.println("7 - Listagem com subtotal");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            escolha = input.nextInt();
            input.nextLine();

            switch (escolha) {
                case 1:
                    if (qtdProdutos < TAM) {
                        estoque[qtdProdutos] = new Produto();
                        cadastrarProduto(estoque[qtdProdutos]);
                        qtdProdutos++;
                        System.out.println("Produto cadastrado com sucesso!");
                    } else {
                        System.out.println("Estoque cheio!");
                    }
                    break;

                case 2:
                    listarProdutos(estoque, qtdProdutos);
                    break;

                case 3:
                    filtrarPorCategoria(estoque, qtdProdutos);
                    break;

                case 4:
                    ordenandoComSelection(estoque, qtdProdutos);
                    listarProdutos(estoque, qtdProdutos);
                    break;

                case 5:
                    qtdProdutos = removendoProduto(estoque, qtdProdutos);
                    break;

                case 6:
                    atulizandoPreco(estoque, qtdProdutos);
                    break;

                case 7:
                    listagemComSubtotal(estoque, qtdProdutos);
                    break;

                case 0:
                    System.out.println("MENU ENCERRADO!");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (escolha != 0);
    }

    public static void cadastrarProduto(Produto p) {
        System.out.println("\n ## CADASTRE SEU PRODUTO ##");
        System.out.print("Nome: ");
        p.nome = input.nextLine();

        System.out.print("Descrição: ");
        p.descricao = input.nextLine();

        System.out.print("Quantidade em estoque: ");
        p.qtdEstoque = input.nextInt();

        System.out.print("Preço unitário: ");
        p.precoUnit = input.nextDouble();

        System.out.print("Quantidade mínima: ");
        p.qtdMin = input.nextInt();
        input.nextLine();

        System.out.print("Categoria: ");
        p.categoria = input.nextLine();
    }

    public static void listarProdutos(Produto[] estoque, int qtd) {
        System.out.println("\n ##LISTA DOS PRODUTOS ##");
        System.out.println("<---------------------->");
        for (int i = 0; i < qtd; i++) {
            System.out.println("Nome: " + estoque[i].nome);
            System.out.println("Categoria: " + estoque[i].categoria);
            System.out.println("Quantidade disponivel no estoque: " + estoque[i].qtdEstoque);
            System.out.println("Preço: R$" + estoque[i].precoUnit);
            System.out.println("Quantidade mínima: " + estoque[i].qtdMin);
            System.out.println("----------------------------");
        }
    }

    public static void filtrarPorCategoria(Produto[] estoque, int qtd) {

        System.out.print("\n QUAL CATEGORIA DESEJA FILTRAR? ");
        String categoria = input.nextLine();

        System.out.println("\n--- CATEGORIA : " + categoria + "' ---");
        boolean encontrou = false;

        for (int i = 0; i < qtd; i++) {
            if (estoque[i].categoria.equalsIgnoreCase(categoria)) {
                System.out.println("Nome: " + estoque[i].nome);
                System.out.println("Quantidade: " + estoque[i].qtdEstoque);
                System.out.println("Preço: R$" + estoque[i].precoUnit);
                System.out.println("-----------");
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("NÃO FOI POSSIVEL ENCONTRAR :/");
        }
    }

    public static void ordenandoComSelection(Produto[] estoque, int qtd) {
        for (int i = 0; i < qtd - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < qtd; j++) {
                if (estoque[j].nome.compareToIgnoreCase(estoque[menor].nome) < 0) {
                    menor = j;
                }
            }
            if (menor != i) {
                Produto temp = estoque[i];
                estoque[i] = estoque[menor];
                estoque[menor] = temp;
            }
        }
        System.out.println(" SEUS PRODUTOS FORAM ORDENADOS EM ORDEM ALFABETICA: ");
    }

    public static int removendoProduto(Produto[] estoque, int qtd) {
        System.out.print("\n QUAL PRODUTO DESEJA REMOVER? ");
        String nome = input.nextLine();

        for (int i = 0; i < qtd; i++) {
            if (estoque[i].nome.equalsIgnoreCase(nome)) {
                for (int j = i; j < qtd - 1; j++) {
                    estoque[j] = estoque[j + 1];
                }
                System.out.println("SEU PRODUTO FOI REMOVIDO ;)");
                return qtd - 1;
            }
        }
        System.out.println("O PRODUTO QUE VOCE DESEJA REMOVER NÃO FOI ENCONTRADO !");
        return qtd;
    }
    public static void atulizandoPreco(Produto[] estoque, int qtd) {
        System.out.print("\n QUAL PRODUTO VOCE DESEJA ATUALIZAR O PREÇO? ");
        String nome = input.nextLine();

        for (int i = 0; i < qtd; i++) {
            if (estoque[i].nome.equalsIgnoreCase(nome)) {
                System.out.print("INSIRA O NOVO VALOR: ");
                estoque[i].precoUnit = input.nextDouble();
                input.nextLine();
                System.out.println("ATUALIZADO COM SUCESSO!");
                return;
            }
        }
        System.out.println("O PRODUTO QUE VOCE DESEJA ATUALIZAR NÃO FOI ENCONTRATO :/");
    }
    public static void listagemComSubtotal(Produto[] estoque, int qtd) {
        if (qtd == 0) {
            System.out.println("NÃO CADASTROU O PRODUTO!");
            return;
        }
        for (int i = 0; i < qtd - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < qtd; j++) {
                if (estoque[j].categoria.compareToIgnoreCase(estoque[menor].categoria) < 0) {
                    menor = j;
                }
            }
            if (menor != i) {
                Produto temp = estoque[i];
                estoque[i] = estoque[menor];
                estoque[menor] = temp;
            }
        }
        System.out.println("\n--- LISTAGEM POR CATEGORIA ---");

        String atualCategoria = estoque[0].categoria;
        double subtotal = 0;
        double totalGeral = 0;

        System.out.println("\nCATEGORIA: " + atualCategoria);
        System.out.println("----------------------------------");

        for (int i = 0; i < qtd; i++) {
            if (!estoque[i].categoria.equalsIgnoreCase(atualCategoria)) {
                System.out.printf("SUBTOTAL: R$%.2f\n\n", subtotal);
                totalGeral += subtotal;
                subtotal = 0;
                atualCategoria = estoque[i].categoria;
                System.out.println("CATEGORIA: " + atualCategoria);
                System.out.println("----------------------------------");
            }
            double valorItem = estoque[i].qtdEstoque * estoque[i].precoUnit;
            subtotal += valorItem;
            System.out.printf("%s - %d - R$%.2f\n",estoque[i].nome, estoque[i].qtdEstoque, estoque[i].precoUnit);
        }
        System.out.printf("SUBTOTAL: R$%.2f\n", subtotal);
        totalGeral += subtotal;

        System.out.printf("\nTOTAL GERAL: R$%.2f\n", totalGeral);
    }
}