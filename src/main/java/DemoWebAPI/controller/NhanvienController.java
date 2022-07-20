package DemoWebAPI.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.time.LocalDate;
import java.util.Date;

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

import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


import DemoWebAPI.model.WFH;
import DemoWebAPI.model.NhanVien;
import DemoWebAPI.model.NghiPhep;
import DemoWebAPI.model.OT;
import DemoWebAPI.repository.NhanVienRepository;
import DemoWebAPI.repository.WFHRepository;
import DemoWebAPI.model.ApiResponse;

import DemoWebAPI.repository.NghiPhepRepository;
import DemoWebAPI.repository.OTRepository;



@RestController
@CrossOrigin
@RequestMapping(value = "/api")

public class NhanvienController {
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
	
	@GetMapping("/xemnhanvien")
	public ResponseEntity<List<NhanVien>> XemDanhSachNhanVien() {
		try {
			List<NhanVien> nhanvienlst = new ArrayList<NhanVien>();

			repoNV.findAll().forEach(nhanvienlst::add);

			if (nhanvienlst.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(nhanvienlst, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping("/request_wfh")
	public ResponseEntity<WFH> Request_wfh(@RequestBody WFH wfh) {
		try {
			wfh.setID(repoWFH.count()+1);
			WFH _wfh = repoWFH.save(new WFH(wfh.getID(), wfh.getMaNguoiDuyet(), wfh.getMaNhanVien(), wfh.getNgayBatDau(), wfh.getNgayKetThuc(), wfh.getLyDo(), 
					wfh.getLyDoTuChoi(), wfh.getTrangThai()));
			return new ResponseEntity<>(_wfh, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	@PutMapping("/approve_request_wfh/{id_wfh}")
//	public ResponseEntity<WFH> Approve_request_wfh(@PathVariable(value = "id_wfh") long id_wfh) {
//		//System.out.println("Hello " + id_wfh);
//		try {						
//			Query q = new Query();
//			q.addCriteria(Criteria.where("ID").is(id_wfh));
//			WFH wfh = mongoTemplate.findOne(q, WFH.class);
//			wfh.setTrangThai("1");			
//			return new ResponseEntity<>(repoWFH.save(wfh), HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
	@PutMapping("/approve_request_wfh")
	public ResponseEntity<WFH> Approve_request_wfh(@RequestParam (value ="id", required=false) long id, @RequestParam (value ="manguoiduyet", required=false) long manguoiduyet) {
		
		try {						
			Query q = new Query();
			q.addCriteria(Criteria.where("ID").is(id));
			WFH wfh = mongoTemplate.findOne(q, WFH.class);
			wfh.setTrangThai("1");			
			return new ResponseEntity<>(repoWFH.save(wfh), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	@PutMapping("/reject_request_wfh/{id_wfh}")
//	public ResponseEntity<WFH> Reject_request_wfh(@PathVariable(value = "id_wfh") long id_wfh,
//			@RequestParam ("lydotuchoi") String lydotuchoi) {
//		try {						
//			Query q = new Query();
//			q.addCriteria(Criteria.where("ID").is(id_wfh));
//			WFH wfh = mongoTemplate.findOne(q, WFH.class);
//			wfh.setTrangThai("2");	
//			wfh.setLyDoTuChoi(lydotuchoi);
//			return new ResponseEntity<>(repoWFH.save(wfh), HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
	@PutMapping("/reject_request_wfh")
	public ResponseEntity<WFH> Reject_request_wfh(@RequestParam (value ="id", required=false) long id, @RequestParam (value ="manguoiduyet", required=false) long manguoiduyet,  @RequestParam (value ="lydotuchoi", required=false) String lydotuchoi) {
		try {						
			Query q = new Query();
			q.addCriteria(Criteria.where("ID").is(id));
			WFH wfh = mongoTemplate.findOne(q, WFH.class);
			wfh.setTrangThai("2");	
			wfh.setLyDoTuChoi(lydotuchoi);
			return new ResponseEntity<>(repoWFH.save(wfh), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//nghiphep
	@PostMapping("/request_nghiphep")
	public ResponseEntity<NghiPhep> Request_nghiphep(@RequestBody NghiPhep np) {
		try {
			np.setID(repoNP.count()+1);
			NghiPhep _np = repoNP.save(new NghiPhep(np.getID(), np.getMaNguoiDuyet(), np.getLoaiNghiPhep(), np.getNgayBatDau(), np.getNgayKetThuc(), np.getLyDo(), 
					np.getLyDoTuChoi(), np.getPhepHopLe(), np.getTrangThai()));
			return new ResponseEntity<>(_np, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//duyet don nghi phep 
	@PutMapping("/approve_request_nghiphep")
	public ResponseEntity<NghiPhep> Approve_request_nghiphep(@RequestParam (value ="id", required=false) long id, @RequestParam (value ="manguoiduyet", required=false) long manguoiduyet) {
		System.out.println("Hello " + id);
		try {						
			Query q = new Query();
			q.addCriteria(Criteria.where("ID").is(id));
			NghiPhep np = mongoTemplate.findOne(q, NghiPhep.class);
			System.out.println("Trang thai don nghi phep: " + np.getTrangThai());
			System.out.println("loai bien: " + np.getNgayBatDau().getClass().getName());
			System.out.println("loai bien: " + np.getNgayKetThuc().getClass().getName());
			//Date dateObject = new Date(np.getNgayBatDau());
			
			
			if (np.getTrangThai() == 0) {
			    np.setTrangThai(1);
			    np.setMaNguoiDuyet(manguoiduyet);
			} 

			else {
				
			}
			System.out.println("Trang thai nghi phep sau khi cap nhat: " + np.getTrangThai());
			//np.setTrangThai();		
			return new ResponseEntity<>(repoNP.save(np), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//tu choi don nghi phep
	@PutMapping("/reject_request_nghiphep")
	public ResponseEntity<NghiPhep> Reject_request_nghiphep(@RequestParam (value ="id", required=false) long id, @RequestParam (value ="manguoiduyet", required=false) long manguoiduyet) {
		System.out.println("Hello " + id);
		try {						
			Query q = new Query();
			q.addCriteria(Criteria.where("ID").is(id));
			NghiPhep np = mongoTemplate.findOne(q, NghiPhep.class);
			if (np.getTrangThai() != 2) {
				np.setTrangThai(2);
			    np.setMaNguoiDuyet(manguoiduyet);
			} 
			else {
				
			}
				
			return new ResponseEntity<>(repoNP.save(np), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
//	@PutMapping("/customer/disable_enable")
//	public ResponseEntity<ApiResponse<Customer>> DisableEnableCustomer(@RequestParam String request_id) {
//		try {
//			Optional<Customer> customer = Prepo.findById(request_id);
//			if (customer.isPresent() == false) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//			Customer cus = customer.get();
//			if (cus.isIs_enabled() == true)
//				cus.setIs_enabled(false);
//			else
//				cus.setIs_enabled(true);
//			Customer cus_new = Prepo.save(cus);
//			ApiResponse<Customer> resp = new ApiResponse<Customer>(0,"Success",cus_new);
//			return new ResponseEntity<>(resp, HttpStatus.OK);
//		} catch (Exception e) {
//			System.out.println(e);
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//	
	
	
	
}

//---------parse string to localdate
//import java.text.ParseException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.time.format.FormatStyle;
//
//public class Main {
//    public static void main(String[] args) throws ParseException {
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        String dateString = "14/07/2018";
//
//        LocalDate localDateObj = LocalDate.parse(dateString, dateTimeFormatter);  //String to LocalDate
//
//        System.out.println(localDateObj.format(dateTimeFormatter));    // 14/07/2018
//        System.out.println(localDateObj.getClass().getName());
//        
//    }
//}


//-------caculate distance between date 
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.util.Date;
//import java.time.Period;
//
//public class HelloWorld {
//
//    public static void main(String []args) {
//        LocalDate dateBefore = LocalDate.of(2022, 12, 31);
//        LocalDate dateAfter = LocalDate.of(2023, 1, 2);
//
//        Period period = Period.between(dateBefore, dateAfter);
//        long daysDiff = Math.abs(period.getDays());
//
//        System.out.println(" The number of days between dates: " + daysDiff);
//    }
//}