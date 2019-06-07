# Factory Method Pattern (Design Patterns)

Um dos 23 padrões de design apresentados pelo "GoF", é também conhecido como Virtual Constructor, possui a intenção de:
> "Definir uma interface para criar um objeto, mas deixar que subclasses decidam qual classe instanciar. 
> O Factory Method delega a instanciação da classe para as subclasses." [gamma](https://www.amazon.com/Design-Patterns-Object-Oriented-Addison-Wesley-Professional-ebook/dp/B000SEIBB8/ref=sr_1_1?__mk_pt_BR=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=Design+Patterns%3A+Elements+of+Reusable+Object-Oriented+Software&qid=1559828033&s=digital-text&sr=1-1)

O foco desse padrão está em uma fábrica abstrata que dita que suas subclasses devem saber criar um objeto que implementa uma determinada interface. 

O padrão é composto por um *Creator*, e seus *Concrete Creators* que criam *Concrete Products* que extendem/implementam *Product*. A ideia é que criadores diferentes consigam criar produtos diferentes que por sua vez se comportam de formas diferentes mas quando a aplicação ou o usuário tem que lidar com esses criadores eles não precisam saber a priori como eles se comportam.

O código a seguir dá um exemplo disso:

```java
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        final Stream<VeiculoFactory> fabricas = Stream.of(VeiculoFactory.HONDA, 
                    VeiculoFactory.CALOI, 
                    VeiculoFactory.AIRBUS);
        
        fabricas.map(VeiculoFactory::criar).forEach(Veiculo::descrever);
    }
}

@FunctionalInterface
interface Veiculo { 
    Veiculo carro = () -> System.out.println("Faço de 0 a 100 em 3,2s"); 
    Veiculo bicicleta = () -> System.out.println("Não causo poluição e permito um ótimo exercício."); 
    Veiculo aviao = () -> System.out.println("Vou de Belo Horizonte a São Paulo em 40 min.");
    
    void descrever(); 
}

@FunctionalInterface
interface VeiculoFactory {
    VeiculoFactory HONDA = () -> Veiculo.carro;
    VeiculoFactory CALOI = () -> Veiculo.bicicleta;
    VeiculoFactory AIRBUS = () -> Veiculo.aviao;
    
    Veiculo criar(); 
}
```

Aqui o método ```Veiculo criar()``` é o *Factory Method*. 

É comum se confundir *Factory Method* com *Abstract Factory*, um outro padrão listado no livro do GoF. Isso acontece por que é comum implementar este padrão utilizando *Factory Methods*. A diferença é que a *Abstract Factory* é uma fábrica que possui acesso a várias fábricas concretas que criam diferentes objetos relacionados a partir de propriedades passadas pelo usuário. No exemplo acima, se solicitassemos a criação de um veículo passando qual o seu tipo, se carro, ou bicicleta ou avião e a fábrica soubesse construir o veículo isso seria uma *Abstract Factory*.
