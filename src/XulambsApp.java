import java.util.LinkedList;
import java.util.List;

public class XulambsApp {
    private List<Pizza> listaPizzas;

    private void limparTela() {
        IO.print("\033[H\033[2J");
    }

    private void pausa(){
        IO.readln("Digite <ENTER> para continuar");
        limparTela();
    }

    private void cabecalho(){
        limparTela();
        IO.println("XULAMBS PIZZA - v0.2");
        IO.println("=====================");
        IO.println("Pizzas vendidas hoje: " +
                    Pizza.getPizzasVendidas());
    }

    private int exibirMenu() {
        cabecalho();
        IO.println("1 - Abrir pedido");
        IO.println("2 - Alterar pedido");
        IO.println("3 - Relatório de um pedido");
        IO.println("4 - Encerrar pedido");
        IO.println("0 - Sair");
        return Integer.parseInt(IO.readln("Digite sua opção: "));
    }



    Pizza comprarPizza(){
        cabecalho();
        int adicionais = 
            Integer.parseInt(IO.readln("Quantos ingredientes? "));
    
        Pizza nova = new Pizza();
        nova.adicionarIngredientes(adicionais);

        mostrarNota(nova);
        return nova;
    }

    void mostrarNota(Pizza pizza){
        IO.println("Pizza comprada:");
        IO.println(pizza.gerarCupom());
        IO.println("=====================");
    }

    void mostrarPizzas(){
        cabecalho();
        for (Pizza pizza : listaPizzas) {
            mostrarNota(pizza);
        }
    }

    void abrirPedido(){
        Pedido novoPedido = new Pedido();
        String novaPizza;
        do {
            Pizza pizza = comprarPizza();
            novoPedido.adicionarPizza(pizza);
            novaPizza = IO.readln("Mais pizza? (s/n)");
        } while (novaPizza.equals("s"));
        exibirRelatorio(novoPedido);
        armazenarPedido(novoPedido);
    }

    void alterarPedido(){
        Pedido buscado = localizarPedido();
        if(buscado != null){
            Pizza pizza = comprarPizza();
            buscado.adicionarPizza(pizza);
            exibirRelatorio(buscado);
        }
    }

    void relatorioPedido(){
        Pedido buscado = localizarPedido();
        if(buscado != null){
            exibirRelatorio(buscado);
        }
    }

     void encerrarPedido(){
        Pedido buscado = localizarPedido();
        if(buscado != null){
            buscado.fecharPedido();
            exibirRelatorio(buscado);
        }
    }
    void main(){
        int opcao;
        listaPizzas = new LinkedList<>();
        do {
            opcao = exibirMenu();
            switch (opcao) {
                case 1 -> abrirPedido();
                case 2 -> alterarPedido();
                case 3 -> relatorioPedido();
                case 4 -> encerrarPedido();
                case 0 -> IO.println("Encerrando!");
                default -> IO.println("Opção inválida");
            }   
            pausa(); 
        } while (opcao != 0);
        

    }

    public void acrescentarBorda(Pizza pizza){
        pizza.adicionarBorda(EBorda.CHOCOLATE);
    }
    
}
