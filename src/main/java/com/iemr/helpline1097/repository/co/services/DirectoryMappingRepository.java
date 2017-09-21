package com.iemr.helpline1097.repository.co.services;

import java.util.Objects;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iemr.helpline1097.data.co.services.DirectoryMapping;

@Repository
public interface DirectoryMappingRepository extends CrudRepository<DirectoryMapping, Long>
{
	// @Query("select m.instituteDirMapID, i.institutionID, i.institutionName "
	// + "from DirectoryMapping m, InstitutionDetails i "
	// + "where m.deleted = false and m.instituteDirectoryID =
	// :instituteDirectoryID and "
	// + "m.instituteSubDirectoryID = :instituteSubDirectoryID and "
	// + "m.institutionID = i.institutionID and i.stateID = :stateID and " +
	// "i.districtID = :districtID and "
	// + "i.districtBranchMappingID = :districtBranchMappingID and i.deleted =
	// false")

	@Query("select m.instituteDirMapID, i.institutionID, i.institutionName, i.address, i.contactNo1, i.contactNo2, "
			+ "i.contactNo3 from DirectoryMapping m JOIN m.institutionDetails i " + " where m.deleted = false and "
			+ "m.instituteDirectoryID = :instituteDirectoryID and "
			+ "m.instituteSubDirectoryID = :instituteSubDirectoryID and "
			+ "i.stateID = :stateID and i.districtID = :districtID and "
			+ "i.districtBranchMappingID = :districtBranchMappingID and i.deleted = false")

	public Set<Objects[]> findAciveInstituteDirectories(@Param("instituteDirectoryID") int instituteDirectoryID,
			@Param("instituteSubDirectoryID") int instituteSubDirectoryID, @Param("stateID") int stateID,
			@Param("districtID") int districtID, @Param("districtBranchMappingID") int districtBranchMappingID);

	@Query("select m.instituteDirMapID, i.institutionID, i.institutionName, i.address, i.contactNo1, i.contactNo2, "
			+ "i.contactNo3 from DirectoryMapping m JOIN m.institutionDetails i " + " where m.deleted = false and "
			+ "m.instituteDirectoryID = :instituteDirectoryID and "
			+ "m.instituteSubDirectoryID = :instituteSubDirectoryID and "
			+ "i.stateID = :stateID and i.districtID = :districtID and i.deleted = false")

	public Set<Objects[]> findAciveInstituteDirectories(@Param("instituteDirectoryID") int instituteDirectoryID,
			@Param("instituteSubDirectoryID") int instituteSubDirectoryID, @Param("stateID") int stateID,
			@Param("districtID") int districtID);
}