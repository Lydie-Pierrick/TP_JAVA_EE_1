package com.codetroopers.eput.services;

import com.codetroopers.eput.domain.GoldenBookEntryDAO;
import com.codetroopers.eput.domain.entities.GoldenBookEntry;
import com.codetroopers.eput.models.UserInfo;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * This class is annotated with the Stateless marker. It allows to automatically handle transactions.
 */
@Stateless
public class GoldenBookEntryService {
    @Inject
    UserInfo userInfo;
    @Inject
    GoldenBookEntryDAO bookEntryDAO;
    @Inject
    FacesContext facesContext;


    public GoldenBookEntry insertNewGoldenBookEntry(final GoldenBookEntry entry) {
        if(bookEntryDAO.save(entry) == null){
            facesContext.addMessage("Invalid note", new FacesMessage("The value of note should be between 0 and 10 !"));
        }

        return entry;
    }

    public boolean deleteGoldenBookEntry(final Integer entryId){
//        if( bookEntryDAO.delete(entry) == true){
//            facesContext.addMessage("Successful deletion", new FacesMessage("The entry has been deleted."));
//            return true;
//        }else {
//            return false;
//        }
        return bookEntryDAO.delete(entryId);
    }

    @Produces
    @Named
    public List<GoldenBookEntry> loadGoldenBookEntries() {
        return bookEntryDAO.all();
    }

    public Boolean ratingChange(Integer entryId, Integer rating, Long userId) {
        return bookEntryDAO.ratingChange(entryId, rating, userId);
    }
}
