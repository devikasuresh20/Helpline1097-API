package com.iemr.helpline1097.repository.co.beneficiarycall;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;

//import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.iemr.helpline1097.data.co.beneficiarycall.BeneficiaryCall;

public interface BeneficiaryCallRepository extends CrudRepository<BeneficiaryCall, Long>
{
	@Query("select " + "b.benCallID, b.benCallServicesMappingHistories, b.calledServiceID, b.is1097, "
			+ "b.callTime, b.remarks, b.callClosureType, b.dispositionStatusID from BeneficiaryCall b "
			+ "where b.benCallID = :id order by b.benCallID desc")
	public ArrayList<Objects[]> findCallsByBenefeciaryID(@Param("id") long id, Pageable page);

	@Query("select b.benCallID, b.callTime, b.remarks, count(m.subCategoryID), "
			+ "count(m.feedbackID), count(m.instituteDirMapID) , b.createdDate, "
			+ "count(m.coSubCategoryID) from BeneficiaryCall b " + "left join b.benCallServicesMappingHistories m "
			+ "where b.beneficiaryRegID = :id group by b.benCallID order by b.benCallID desc")
	public ArrayList<Objects[]> findCallsHistoryByBenefeciaryID(@Param("id") Long id, Pageable page);

	@Query("select b.benCallID, b.callTime, b.remarks, count(m.subCategoryID), "
			+ "count(m.feedbackID), count(m.instituteDirMapID) , b.createdDate, "
			+ "count(m.coSubCategoryID) from BeneficiaryCall b " + "left join b.benCallServicesMappingHistories m "
			+ "where b.benCallID = :id group by b.benCallID order by b.benCallID desc")
	public ArrayList<Objects[]> findCallSummaryByCallID(@Param("id") Long id);

	@Transactional
	@Modifying
	@Query("update BeneficiaryCall set callTime = :callTime, remarks = :remarks, "
			+ "callClosureType = :callClosureType, " + "dispositionStatusID = :dispositionStatusID, "
			+ "callTypeID = :callTypeID where benCallID = :id")
	public int closeCall(@Param("id") Long id, @Param("remarks") String remarks, @Param("callTime") Timestamp callTime,
			@Param("callClosureType") String callClosureType,
			@Param("dispositionStatusID") Integer dispositionStatusID);

	@Transactional
	@Modifying
	@Query("update BeneficiaryCall set beneficiaryRegID = :beneficiaryRegID where benCallID = :id")
	public int updateBeneficiaryIDInCall(@Param("id") Long id, @Param("beneficiaryRegID") Long beneficiaryRegID);

}