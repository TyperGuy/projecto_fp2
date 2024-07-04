/*------------------------------------
Tema: Gestão de aluguer de espaço de Co-working
Nome: Raimundo Tony
Numero: 20830
Ficheiro: Analise.java
Data: 25.05.2024
--------------------------------------*/

/*
 * 1. Objectivo
 * Este projecto tem o objectivo de registar a entrada e saida de
 * pessoas/empresas dentro de um espaço de co-woking, bem como sua localização
 * dentro do espaço e gestão das sala disponiveis
 * 
 * 2. Visao [Interfaces Graficas]
 * - ApresentacaoVisao
 * - LoginVisao
 * - MenuPrincipal
 * - CadaverVisao
 * - EntradaVisao
 * - SaidaVisao
 * 
 * 3. Entidades Fortes e Seus Atributos (Modelo)
 * - CadaverModelo
 * int id
 * String nome
 * String tipo_documento [Fk]
 * String numero_documento
 * String dataNascimento
 * String nacionalidade [Fk]
 * String genero
 * - EntradaModelo
 * int id
 * CadaverModelo cadaver [Fk]
 * String dataMorte
 * String causaMorte [Fk]
 * String dataEntrada
 * String horaEntrada
 * String nomeFamiliar
 * String telefone
 * String nomeFuncionario [Fk]
 * int numero_gaveta
 * 
 * - SaidaModelo
 * int id
 * EntradaModelo entrada
 * String dataSaida
 * String horaSaida
 * String nomeFamiliar
 * String telefone
 * String nomeFuncionario [Fk]
 * 
 * 4. Ficheiro | persistencia de dados
 * - Cadavers.dat
 * - Entrada.dat
 * - Saida.dat
 * 
 * 5. Tabelas de Apoio (Auxiliares) = Entidades Fracas
 * - Nacionalidades.tab
 * - Funcionarios.tab
 * - TipoDocumentos.tab
 * - CausaDaMorte.tab
 * 
 * 6. Listagens
 * - Listagem geral de Cadavers
 * - Pesquisar Cadaver por Nome
 * 
 * 7. Diversos
 * 7.1 - Implementação: Java Swing
 * 7.2 - IDE: Notepad++
 */