package model;

import java.math.BigDecimal;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public BigDecimal getSalariosMinimos(BigDecimal salarioMinimo) {
        return salario.divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public String toString() {
        return String.format("Nome: %s | Data Nascimento: %s | Salário: %s | Função: %s",
                getNome(), getDataNascimentoFormatada(), 
                String.format("%,.2f", salario).replace(",", "X").replace(".", ",").replace("X", "."),
                funcao);
    }
}
