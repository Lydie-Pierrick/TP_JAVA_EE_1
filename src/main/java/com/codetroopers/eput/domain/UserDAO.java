/*
 * Copyright 2016 Code-Troopers.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codetroopers.eput.domain;

import com.codetroopers.eput.domain.entities.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by cgatay on 19/01/16.
 */
//tag::class[]
@Stateless
public class UserDAO {
    @Inject
    private EntityManager em;

    //tag::allMethod[]
    public List<User> all(){
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
    //end::allMethod[]

    public User getUserByName(String name){
        String s = "SELECT u FROM User u WHERE NAME=?";
        Query query = em.createQuery(s, User.class);
        query.setParameter(1, name);

        List<User> result = query.getResultList();

        if(!result.isEmpty())
            return result.get(0);
        else
            return null;
    }

    public boolean isValidLogin(String name, String password){
        User user  = getUserByName(name);

        if(user == null)
            return false;
        else{
            if(user.password.equals(password)) {
                return true;
            }else
                return false;
        }
    }
    public User create() {
        User user = new User("NAME", "name@code-troopers.com");
        em.persist(user);
        return user;
    }

    public User save(User user) {
        em.persist(user);
        return user;
    }

    public boolean delete(User user){
        if (user != null) {
            Query q = em.createQuery("DELETE FROM User WHERE id =" + user.getId());
            q.executeUpdate();
            return true;
        }else{
            return false;
        }
    }
}
//end::class[]
