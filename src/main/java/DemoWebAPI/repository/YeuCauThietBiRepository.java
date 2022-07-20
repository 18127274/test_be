package DemoWebAPI.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import DemoWebAPI.model.YeuCauThietBi;

@Repository
public interface YeuCauThietBiRepository extends MongoRepository<YeuCauThietBi, String> {

}
