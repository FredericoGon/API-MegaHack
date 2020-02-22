package com.stone.meSalva.repositories;

import com.stone.meSalva.models.Emprestimo;
import com.stone.meSalva.models.Estabelecimento;
import com.stone.meSalva.models.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class DbSeeder implements CommandLineRunner {

    @Autowired
    PasswordEncoder passwordEncoder;

    private EmprestimoRepository emprestimoRepository;
    private EstabelecimentoRepository estabelecimentoRepository;
    private UsuarioRepository usuarioRepository;

    public DbSeeder(EmprestimoRepository emprestimoRepository, EstabelecimentoRepository estabelecimentoRepository, UsuarioRepository usuarioRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.estabelecimentoRepository = estabelecimentoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Emprestimo primeiro = new Emprestimo(
            "04613874930",
            30.00,
            new Date(),
            null,
            null,
            false,
            "83545731000140",
            null
        );

        Emprestimo segundo = new Emprestimo(
                "04613874940",
                20.00,
                new Date(),
                null,
                null,
                false,
                "83545731000150",
                null
        );

        GeoJsonPoint localNibles = new GeoJsonPoint(-26.8973119, -49.0670186);
        Estabelecimento primeiroEstabelecimento = new Estabelecimento("83545731000140", "Nibbles", localNibles);
        GeoJsonPoint localTecnofestas = new GeoJsonPoint(-26.902249, -49.0709239);
        Estabelecimento segundoEstabelecimento = new Estabelecimento("83545731000150", "TecnoFestas", localTecnofestas);

        List<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        Usuario primeiroUsuario = new Usuario("José", "04613874930", this.passwordEncoder.encode("123456"), "Jose", roles);

        List<String> roles2 = new ArrayList<>();
        roles2.add("ROLE_USER");
        roles2.add("ROLE_ADMIN");
        Usuario segundoUsuario = new Usuario("Maria", "04613874940", this.passwordEncoder.encode("996633"), "Maria", roles2);

        this.emprestimoRepository.deleteAll();
        this.estabelecimentoRepository.deleteAll();
        this.usuarioRepository.deleteAll();

        List<Emprestimo> emprestimos = Arrays.asList(primeiro, segundo);
        this.emprestimoRepository.saveAll(emprestimos);

        List<Estabelecimento> estabelecimentos = Arrays.asList(primeiroEstabelecimento, segundoEstabelecimento);
        this.estabelecimentoRepository.saveAll(estabelecimentos);

        List<Usuario> usuarios = Arrays.asList(primeiroUsuario, segundoUsuario);
        this.usuarioRepository.saveAll(usuarios);
    }
}
