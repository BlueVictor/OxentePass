package com.oxentepass.oxentepass.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class EventoControllerTest {
    
    @Autowired
    private EventoController eventoController;

    @Test
    @Rollback
    public void test() {
        // Finalizar
    }
}