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


    public boolean insertNewGoldenBookEntry(final GoldenBookEntry entry) {
        if(bookEntryDAO.save(entry) == null){
            facesContext.addMessage("Invalid note", new FacesMessage("The value of note should be between 0 and 10 !"));
            return false;
        }else {
            return true;
        }
    }

    public boolean deleteGoldenBookEntry(final GoldenBookEntry entry){
        return bookEntryDAO.delete(entry);
    }

    @Produces
    @Named
    public List<GoldenBookEntry> loadGoldenBookEntries() {
        return bookEntryDAO.all();
    }
}
