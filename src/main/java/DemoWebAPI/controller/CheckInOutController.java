package DemoWebAPI.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DemoWebAPI.model.OT;
import DemoWebAPI.model.Check_in_out;
import DemoWebAPI.model.NhanVien;
import DemoWebAPI.repository.NhanVienRepository;
import DemoWebAPI.repository.Check_in_outRepository;
import DemoWebAPI.model.ApiResponse;
import DemoWebAPI.repository.NghiPhepRepository;
import DemoWebAPI.repository.OTRepository;



@RestController
@CrossOrigin
@RequestMapping(value = "/api")

public class CheckInOutController {
	@Autowired
	NhanVienRepository repoNV;
	@Autowired
	Check_in_outRepository repoCheckIn;
	@Autowired
	NghiPhepRepository repoNP;
	@Autowired
	OTRepository repoOT;
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	MongoOperations mongoOperation;
	
//	@GetMapping("/get_ot_id/{MaOT_input}")
//	public ResponseEntity<ApiResponse<List<OT>>> XemDSOT_ID(@PathVariable(value = "MaOT_input") String MaOT_input) {
//
//		try {
//			List<OT> otlst = new ArrayList<OT>();
//			Query q = new Query();
//			q.addCriteria(Criteria.where("ID").is(MaOT_input));
//			otlst = mongoTemplate.find(q, OT.class);
//			if (otlst.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//			ApiResponse<List<OT>> resp = new ApiResponse<List<OT>>(0,"Success",otlst);
//			return new ResponseEntity<>(resp, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
//	@GetMapping("/get_time")
//	public ResponseEntity<ApiResponse<List<Check_in_out>>> CheckinTimeMonthYear(@RequestParam ("Month_input") int Month_input) {
//
//		try {
//			List<Check_in_out> checklst = new ArrayList<Check_in_out>();
//
//			repoCheckIn.findAll().forEach(checklst::add);
//			
////			Query q = new Query();
////			q.addCriteria(Criteria.where("MaNhanVien").is(MaNV_input));
////			otlst = mongoTemplate.find(q, Check_in_out.class);
//			if (checklst.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//			ApiResponse<List<Check_in_out>> resp = new ApiResponse<List<Check_in_out>>(0,"Success",checklst);
//			return new ResponseEntity<>(resp, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
	@GetMapping("/get_checkin_nv/{MaNV_input}")
	public ResponseEntity<ApiResponse<List<Check_in_out>>> XemDSCheckIn_MaNV(@PathVariable(value = "MaNV_input") String MaNV_input) {

		try {
			List<Check_in_out> checklst = new ArrayList<Check_in_out>();
			Query q = new Query();
			q.addCriteria(Criteria.where("MaNV").is(MaNV_input));
			checklst = mongoTemplate.find(q, Check_in_out.class);
			if (checklst.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			ApiResponse<List<Check_in_out>> resp = new ApiResponse<List<Check_in_out>>(0,"Success",checklst);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/check_out")
	public <Int> ResponseEntity<ApiResponse<Long>> check_out(@RequestParam ("MaCheckIn_input") String MaCheckIn_input, 
			@RequestParam ("GioCheckOut")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime GioCheckOut) {
		try {
			Query q = new Query();
			q.addCriteria(Criteria.where("ID").is(MaCheckIn_input));
			Check_in_out checkout = mongoTemplate.findOne(q, Check_in_out.class);
			checkout.setGioKetThuc(GioCheckOut);
			int month = checkout.getGioBatDau().getMonthValue();
			long hours = checkout.getGioBatDau().until(LocalDateTime.now(), ChronoUnit.HOURS);
			ApiResponse<Long> resp = new ApiResponse<Long>(0,"Success",hours);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/check_in")
	public ResponseEntity<ApiResponse<Check_in_out>> check_in(@RequestBody Check_in_out check_in) {
		try {
			check_in.setID(UUID.randomUUID().toString());
			Check_in_out _check_in = repoCheckIn.save(new Check_in_out(check_in.getID(), check_in.getMaNV(), check_in.getGioBatDau()));
			ApiResponse<Check_in_out> resp = new ApiResponse<Check_in_out>(0,"Success",_check_in);
			return new ResponseEntity<>(resp, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
