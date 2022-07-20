package DemoWebAPI.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
import DemoWebAPI.repository.NhanVienRepository;
import DemoWebAPI.repository.WFHRepository;
import DemoWebAPI.model.ApiResponse;
import DemoWebAPI.repository.NghiPhepRepository;
import DemoWebAPI.repository.OTRepository;



@RestController
@CrossOrigin
@RequestMapping(value = "/api")

public class OTController {
	@Autowired
	NhanVienRepository repoNV;
	@Autowired
	WFHRepository repoWFH;
	@Autowired
	NghiPhepRepository repoNP;
	@Autowired
	OTRepository repoOT;
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	MongoOperations mongoOperation;
	
	@GetMapping("/get_ot_id/{MaOT_input}")
	public ResponseEntity<ApiResponse<List<OT>>> XemDSOT_ID(@PathVariable(value = "MaOT_input") String MaOT_input) {

		try {
			List<OT> otlst = new ArrayList<OT>();
			Query q = new Query();
			q.addCriteria(Criteria.where("ID").is(MaOT_input));
			otlst = mongoTemplate.find(q, OT.class);
			if (otlst.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			ApiResponse<List<OT>> resp = new ApiResponse<List<OT>>(0,"Success",otlst);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/unaccepted_ot")
	public ResponseEntity<ApiResponse<List<OT>>> XemDSOTChuaDuyet() {
		try {
			List<OT> otlst = new ArrayList<OT>();
            Query q = new Query();
            q.addCriteria(Criteria.where("TrangThai").is("0"));
            otlst = mongoTemplate.find(q, OT.class);

			if (otlst.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			ApiResponse<List<OT>> resp = new ApiResponse<List<OT>>(0,"Success",otlst);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/get_ot_nv/{MaNV_input}")
	public ResponseEntity<ApiResponse<List<OT>>> XemDSOT_MaNV(@PathVariable(value = "MaNV_input") String MaNV_input) {

		try {
			List<OT> otlst = new ArrayList<OT>();
			Query q = new Query();
			q.addCriteria(Criteria.where("MaNhanVien").is(MaNV_input));
			otlst = mongoTemplate.find(q, OT.class);
			if (otlst.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			ApiResponse<List<OT>> resp = new ApiResponse<List<OT>>(0,"Success",otlst);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/accept_ot")
	public ResponseEntity<ApiResponse<OT>> TiepNhanOT(@RequestParam ("MaOT_input") String MaOT_input, 
			@RequestParam ("TinhTrang_input") String TinhTrang_input, @RequestParam ("LyDoTuChoi_input") String LyDoTuChoi_input, 
			@RequestParam ("MaNguoiDuyet_input") String MaNguoiDuyet_input) {
		try {
			Query q = new Query();
			q.addCriteria(Criteria.where("ID").is(MaOT_input));
			OT ot = mongoTemplate.findOne(q, OT.class);
			ot.setLyDoTuChoi(LyDoTuChoi_input);
			ot.setTrangThai(TinhTrang_input);
			ot.setMaNguoiDuyet(MaNguoiDuyet_input);
			ApiResponse<OT> resp = new ApiResponse<OT>(0,"Success",repoOT.save(ot));
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/request_ot")
	public ResponseEntity<ApiResponse<OT>> Request_ot(@RequestBody OT ot) {
		try {
			//DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			//String ngayot = ot.getNgayOT();
			//LocalDate localDateObj = LocalDate.parse(ot.getNgayOT(), dateTimeFormatter);  //String to LocalDate
			//System.out.println(localDateObj.format(dateTimeFormatter));    // 14/07/2018
	       // System.out.println(localDateObj.getClass().getName());
			ot.setID(UUID.randomUUID().toString());
			OT _ot = repoOT.save(new OT(ot.getID(), ot.getMaNhanVien() ,ot.getNgayOT(), ot.getSoGio(), ot.getLyDoOT()));
			ApiResponse<OT> resp = new ApiResponse<OT>(0,"Success",_ot);
			return new ResponseEntity<>(resp, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
