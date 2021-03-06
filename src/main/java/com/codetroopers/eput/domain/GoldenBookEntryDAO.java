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

import com.codetroopers.eput.domain.entities.GoldenBookEntry;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


@Stateless
public class GoldenBookEntryDAO {
    @Inject
    EntityManager em;

    public List<GoldenBookEntry> all() {
        return em.createQuery("SELECT gb FROM GoldenBookEntry gb", GoldenBookEntry.class).getResultList();
    }

    public GoldenBookEntry save(GoldenBookEntry entry){
        if(entry.getNote() <= 10  && entry.getNote() >= 0) {
            em.persist(entry);
            return entry;
        }
        return null;
    }

    public boolean delete(final Integer entryId){
        if (entryId != null) {
//            em.getTransaction().begin();
//            em.remove(entry);
//            em.getTransaction().commit();
            Query q = em.createQuery("DELETE FROM GoldenBookEntry WHERE id =" + entryId);
            q.executeUpdate();
            return true;
        }else{
            return false;
        }
    }

    public Boolean ratingChange(Integer entryId, Integer rating, Long userId) {
        if(entryId != null && rating != null && userId != null)
        {
            Query q = em.createQuery("UPDATE GoldenBookEntry SET note =" + rating + "WHERE id =" + entryId + "AND author =" + userId);
            q.executeUpdate();
            return true;
        }

        return false;
    }
}
