package DemoWebAPI.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import DemoWebAPI.model.NghiPhep;

@Repository
public interface NghiPhepRepository extends MongoRepository<NghiPhep, String> {

}
