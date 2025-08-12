package principal;

import model.Funcionario;
import model.Pessoa;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // 3.1 – Inserir todos os funcionários
        funcionarios.add(new Funcionario("Maria", LocalDate.parse("18/10/2000", formatter), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.parse("12/05/1990", formatter), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Cabo", LocalDate.parse("02/05/1961", formatter), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.parse("14/10/1988", formatter), new BigDecimal("1313.080"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.parse("05/01/1995", formatter), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.parse("19/11/1999", formatter), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.parse("31/03/1993", formatter), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.parse("08/07/1984", formatter), new BigDecimal("3017.45"), "Greente"));
        funcionarios.add(new Funcionario("Hebóisa", LocalDate.parse("24/05/2003", formatter), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.parse("02/09/1996", formatter), new BigDecimal("2799.93"), "Greente"));

        // 3.2 – Remover o funcionário "João"
        funcionarios.removeIf(f -> f.getNome().equals("João"));

        // 3.3 – Imprimir todos os funcionários
        System.out.println("\n3.3 - Todos os funcionários:");
        funcionarios.forEach(System.out::println);

        // 3.4 – Aumento de 10% no salário
        funcionarios.forEach(f -> {
            BigDecimal novoSalario = f.getSalario().multiply(new BigDecimal("1.10"));
            f.setSalario(novoSalario.setScale(2, BigDecimal.ROUND_HALF_UP));
        });

        // 3.5 e 3.6 – Agrupar e imprimir por função
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        System.out.println("\n3.6 - Funcionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("\n" + funcao + ":");
            lista.forEach(System.out::println);
        });

        // 3.8 – Funcionários que fazem aniversário em outubro (10) e dezembro (12)
        System.out.println("\n3.8 - Funcionários com aniversário em outubro e dezembro:");
        funcionarios.stream()
                .filter(f -> f.getMesNascimento() == 10 || f.getMesNascimento() == 12)
                .forEach(System.out::println);

        // 3.9 – Funcionário com maior idade
        System.out.println("\n3.9 - Funcionário com maior idade:");
        Funcionario maisVelho = Collections.max(funcionarios, 
                Comparator.comparingInt(Pessoa::getIdade));
        System.out.printf("%s - %d anos%n", maisVelho.getNome(), maisVelho.getIdade());

        // 3.10 – Funcionários em ordem alfabética
        System.out.println("\n3.10 - Funcionários em ordem alfabética:");
        funcionarios.stream()
                .sorted(Comparator.comparing(Pessoa::getNome))
                .forEach(System.out::println);

        // 3.11 – Total dos salários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.printf("\n3.11 - Total dos salários: %s%n", 
                String.format("%,.2f", totalSalarios).replace(",", "X").replace(".", ",").replace("X", "."));

        // 3.12 – Salários em salários mínimos
        System.out.println("\n3.12 - Salários em salários mínimos:");
        funcionarios.forEach(f -> {
            BigDecimal salariosMinimos = f.getSalariosMinimos(salarioMinimo);
            System.out.printf("%s: %s salários mínimos%n", 
                    f.getNome(), 
                    String.format("%,.2f", salariosMinimos).replace(",", "X").replace(".", ",").replace("X", "."));
        });
    }
}
