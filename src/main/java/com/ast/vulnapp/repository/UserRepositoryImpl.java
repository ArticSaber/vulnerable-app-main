package com.ast.vulnapp.repository;

import com.ast.vulnapp.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements CustomerUserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        String myCustomComplicatedQuery = "SELECT id, email, password, full_name FROM users WHERE email = '"
                + email
                + "' AND password = '"
                + password + "'";
        Query nativeQuery = this.em.createNativeQuery(String.format(myCustomComplicatedQuery), User.class);

        List<User> result = nativeQuery.getResultList();

        if(result == null || result.size() == 0){
            return Optional.empty();
        }

        return Optional.of(result.get(0));
    }
}