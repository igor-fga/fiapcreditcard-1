package com.fiapcreditcard.clientBatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

@SpringBootApplication
@EnableBatchProcessing

public class ClientBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientBatchApplication.class, args);
	}

	@Bean
	public FlatFileItemReader<Aluno> itemReaderAluno(@Value("${fileAlunos.chunk}") Resource resource){
		return new FlatFileItemReaderBuilder<Aluno>()
				.name("Aluno item reader")
				.targetType(Aluno.class)
				.resource(resource)
				.delimited().delimiter(";").names("nome", "cartao")
				.build();
	}

	@Bean
	public FlatFileItemReader<Transacao> itemReaderTransacao(@Value("${fileTransacoes.chunk}") Resource resource){
		return new FlatFileItemReaderBuilder<Transacao>()
				.name("Transacao item reader")
				.targetType(Transacao.class)
				.resource(resource)
				.delimited().delimiter(";").names(":aluno_id", ":beneficiario",":valor",":produto")
				.build();
	}

	@Bean
	public ItemProcessor<Aluno, Aluno> itemProcessorAluno(){
		return (aluno) -> {
			aluno.setNome(aluno.getNome().toUpperCase());
			aluno.setCartao(aluno.getCartao());
			return aluno;
		};
	}

	@Bean
	public ItemProcessor<Transacao, Transacao> itemProcessorTransacao(){
		return (transacao) -> {
			transacao.setAluno(transacao.getAluno());
			transacao.setBeneficiario(transacao.getBeneficiario());
			transacao.setValor(transacao.getValor());
			return transacao;
		};
	}

	@Bean
	public JdbcBatchItemWriter<Aluno> itemWriterAluno(DataSource dataSource){
		return new JdbcBatchItemWriterBuilder<Aluno>()
				.dataSource(dataSource)
				.sql("insert into TB_ALUNO (nome, numero_cartao,ativo,data_atualizacao,data_criacao,saldo) values (:nome, :cartao, true, null,CURRENT_TIMESTAMP(),5000)")
				.beanMapped()
				.build();
	}

	@Bean
	public JdbcBatchItemWriter<Transacao> itemWriterTransacao(DataSource dataSource){
		return new JdbcBatchItemWriterBuilder<Transacao>()
				.dataSource(dataSource)
				.sql("insert into TB_TRANSACAO (aluno_id, beneficiario,valor,produto,data_Criacao) values (:aluno, :beneficiario, :valor, :produto,CURRENT_TIMESTAMP())")
				.beanMapped()
				.build();
	}

	@Bean
	public Step step(StepBuilderFactory stepBuilderFactory,
					 ItemReader<Aluno> itemReaderAluno,
					 ItemProcessor<Aluno, Aluno> itemProcessorAluno,
					 ItemWriter<Aluno> itemWriterAluno){
		return stepBuilderFactory.get("Step chunk file -> h2")
				.<Aluno, Aluno>chunk(2)
				.reader(itemReaderAluno)
				.processor(itemProcessorAluno)
				.writer(itemWriterAluno)
				.allowStartIfComplete(true)
				.build();
	}

	@Bean
	public Step stepTransacoes(StepBuilderFactory stepBuilderFactory,
					 ItemReader<Transacao> itemReaderTransacao,
					 ItemProcessor<Transacao, Transacao> itemProcessorTransacao,
					 ItemWriter<Transacao> itemWriterTransacao){
		return stepBuilderFactory.get("Step chunk file -> h2")
				.<Transacao, Transacao>chunk(2)
				.reader(itemReaderTransacao)
				.processor(itemProcessorTransacao)
				.writer(itemWriterTransacao)
				.allowStartIfComplete(true)
				.build();
	}
	@Bean
	public Step stepAluno(StepBuilderFactory stepBuilderFactory,
					  ItemReader<Aluno> itemReaderAluno,
					  ItemProcessor<Aluno, Aluno> itemProcessorAluno,
					  ItemWriter<Aluno> itemWriterAluno){
		return stepBuilderFactory.get("Step chunk file -> h2")
				.<Aluno, Aluno>chunk(2)
				.reader(itemReaderAluno)
				.processor(itemProcessorAluno)
				.writer(itemWriterAluno)
				.allowStartIfComplete(true)
				.build();
	}

	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory,
				   Step stepAluno, Step stepTransacoes){
		return jobBuilderFactory.get("Job chunk")
				.start(stepAluno)
				.next(stepTransacoes)
				.build();
	}


}
