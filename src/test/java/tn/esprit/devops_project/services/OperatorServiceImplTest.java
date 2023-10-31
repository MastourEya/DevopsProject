package tn.esprit.devops_project.services;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@ActiveProfiles("test")
class OperatorServiceImplTest {

    @Autowired
    private OperatorServiceImpl operatorService;
    @Test
    @DatabaseSetup("/data-set/operator-data.xml")
    void retrieveAllOperators() {
        final List<Operator> allOperators = this.operatorService.retrieveAllOperators();
        assertEquals(allOperators.size(), 1);
    }

    @Test
    @DatabaseSetup("/data-set/operator-data.xml")
    void addOperator() {
        Operator operator = new Operator();
        operator.setFname("azza");
        operator.setLname("kouka");
        operator.setPassword("azza");
        operatorService.addOperator(operator);

        final List<Operator> allOperators = this.operatorService.retrieveAllOperators();
        assertEquals(2,allOperators.size());

        final Operator operator1 = this.operatorService.retrieveOperator(2L);
        assertEquals("azza",operator1.getFname());

    }

    @Test
    @DatabaseSetup("/data-set/operator-data.xml")
    void deleteOperator() {
        final Operator operator = this.operatorService.retrieveOperator(1L);
        operatorService.deleteOperator(operator.getIdOperateur());
    }

    @Test
    @DatabaseSetup("/data-set/operator-data.xml")
    void updateOperator() {
        Operator existingOperator = this.operatorService.retrieveOperator(1L);
        existingOperator.setFname("UpdatedFirstName");
        existingOperator.setLname("UpdatedLastName");
        this.operatorService.updateOperator(existingOperator);
        Operator updatedOperator = this.operatorService.retrieveOperator(1L);
        assertEquals("UpdatedFirstName",updatedOperator.getFname());
        assertEquals("UpdatedLastName",updatedOperator.getLname());

    }

    @Test
    @DatabaseSetup("/data-set/operator-data.xml")
    void retrieveOperator() {
        final Operator stock = this.operatorService.retrieveOperator(1L);
        assertEquals("eya",stock.getFname());
    }
    @Test
    @DatabaseSetup("/data-set/operator-data.xml")
    void retrieveOperator_nullId() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            final Operator operator = this.operatorService.retrieveOperator(100L);
        });
    }
}