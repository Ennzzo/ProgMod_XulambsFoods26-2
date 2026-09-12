import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class PizzaTest {

    @Test
    public void adicionaIngredientesCorretamente(){
        //Arrange
        Pizza pizza = new Pizza();

        //Act
        int quantos = pizza.adicionarIngredientes(4);

        //Assert
        assertEquals(4, quantos);
    }

    @Test
    public void testaSePodeAdicionarIngreidente() {
        //Arrange
        Pizza pizza1 = new Pizza();
        //Act
        int quantos = pizza1.adicionarIngredientes(2);

        //Assert
        assertEquals(9, quantos);
    }

    @Test
    public void naoAdicionaIngredientesNegativo() {
        //Arrange
        Pizza pizza1 = new Pizza();
        //Act
        int quantos = pizza1.adicionarIngredientes(-5);

        //Assert
        assertEquals(4, quantos);
    }

    @Test
    public void calculaValorDaPizzaComAdicionais() {
        
        //Act
        double valor = pizza1.valorFinal();

        //Assert
        assertEquals(49, valor, 0.01);
    }


    
}
