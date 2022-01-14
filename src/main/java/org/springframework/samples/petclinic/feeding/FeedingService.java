package org.springframework.samples.petclinic.feeding;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.pet.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class FeedingService {

    FeedingRepository feedRepo;

    @Autowired
    FeedingService(FeedingRepository repo){
        this.feedRepo = repo;
    }

    public List<Feeding> getAll(){
        return feedRepo.findAll();
    }

    public List<FeedingType> getAllFeedingTypes(){
        return feedRepo.findAllFeedingTypes();
    }

    public FeedingType getFeedingType(String typeName) {
        if(feedRepo.getFeedingType(typeName).isPresent()){
            return feedRepo.getFeedingType(typeName).get();
        }
        return null;
        }



        /*
        Implement the "save" method of the power plan management service. If the pet specified in the feeding
plan is not of the type of pet associated with the corresponding type of feeding, an exception of type
"UnfeasibleFeedingException" must be thrown (this class is already created in the feeding package). In
addition, if the exception is thrown, the database should rollback the transaction associated with the save
operation.*/
    @Transactional(rollbackFor = UnfeasibleFeedingException.class)
    public Feeding save(Feeding p) throws UnfeasibleFeedingException {
        if(!(p.getFeedingType().getPetType() == p.getPet().getType())) {
            throw new UnfeasibleFeedingException();
        }     
        else{
            return feedRepo.save(p);
        }

    }

    
}
