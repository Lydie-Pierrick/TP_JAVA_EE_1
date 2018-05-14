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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Stateless
public class GoldenBookEntryDAO {
    @Inject
    EntityManager em;

    public List<GoldenBookEntry> all() {
        return em.createQuery("SELECT gb FROM GoldenBookEntry gb", GoldenBookEntry.class).getResultList();
    }

    public GoldenBookEntry create(String author, String content, Date date, Integer note) {
        GoldenBookEntry bookEntry = new GoldenBookEntry(author, content, date, note);
        em.persist(bookEntry);
        return bookEntry;
    }
    public GoldenBookEntry save(GoldenBookEntry entry){
        if(entry.getNote() <= 10  && entry.getNote() >= 0) {
            em.persist(entry);
            return entry;
        }
        return null;
    }

    public boolean delete(final GoldenBookEntry entry){
        em.remove(entry);
        return true;
//        if (entry != null) {
//            em.remove(entry);
//            return true;
//        }else{
//            return false;
//        }
    }
}
