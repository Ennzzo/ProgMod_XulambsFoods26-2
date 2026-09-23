/** 
* MIT License
*
* Copyright(c) 2024-26 João Caram <caram@pucminas.br>
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*/

import java.time.LocalDate;
import java.util.LinkedList;

public class Pedido {
    private static int ultimoPedido;
    private LocalDate data;
    private LinkedList<Pizza> pizzas;
    private int idPedido;
    private boolean aberto;

    public Pedido(){
        ultimoPedido++;
        data = LocalDate.now();
        pizzas = new LinkedList<>();
        idPedido = ultimoPedido;
        aberto = true;
    }

    private boolean podeAdicionar(){
        return aberto;
    }

    public int adicionarPizza(Pizza pizza){
        if(pizza != null && podeAdicionar()){
            pizzas.add(pizza);
        }
        return pizzas.size();
    }

    public void fecharPedido(){
        aberto = false;
    }

    public int getID(){
        return  idPedido;
    }

    public double precoAPagar(){
        double preco = 0d;
        for (Pizza pizza : pizzas) {
            preco += pizza.valorFinal();
        }
        return preco;
    }

    public String relatorio(){
        StringBuilder cupom = new StringBuilder();
        
        String estado = aberto ? "aberto" : "fechado";
        
        cupom.append( String.format("Pedido nº %d - %s (%s) com %d pizzas\n", idPedido, data, estado, pizzas.size()));
           
        for (Pizza pizza : pizzas) {
            cupom.append(String.format("----\n%s\n", 
                            pizza.gerarCupom()));
        }
        cupom.append(String.format("VALOR: R$ %.2f", 
                            precoAPagar()));

        return cupom.toString();
    }
}
