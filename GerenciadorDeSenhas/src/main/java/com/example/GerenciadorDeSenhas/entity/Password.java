package com.example.GerenciadorDeSenhas.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;


@Entity
@Table(name = "password", schema = "public")
@NoArgsConstructor
public class Password  {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Getter @Setter
    @Column(name = "url")
    private String url;

    @Getter @Setter
    @Column(name = "senha")
    private String senha;

    @Getter @Setter
    @Column(name = "descricao")
    private String descricao;
}
