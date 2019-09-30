package com.revature.servlets;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.revature.model.Employee;
import com.revature.repository.DatabaseConnection;
import com.revature.service.JSONToObject;

public class UploadReimbursementImageServlet extends HttpServlet {

	private static final String SUFFIX = "/";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DatabaseConnection dbc = new DatabaseConnection();
		Employee currentUser = JSONToObject
				.convertEmployeeJSONToObject((String) req.getSession().getAttribute("currentUser"));
		String currentReimbursementID = (String) req.getAttribute("currentReimbursementID");

		BasicAWSCredentials credentials = new BasicAWSCredentials("AKIAII5YOMVBWEUT2KQQ",
				"dJQIPfvOr71o6RLJQMU5mG3mxlyudd54twrIMBi3");
		AmazonS3 s3client = new AmazonS3Client(credentials);

		String userID = String.valueOf(currentUser.getID());

		String fileName = "reimbursementImages" + SUFFIX + "user" + userID;

		createFolder("allen-gworek-llc-image-storage", fileName, s3client);

		fileName += SUFFIX + currentReimbursementID + ".png";
		s3client.putObject(new PutObjectRequest("allen-gworek-llc-image-storage", fileName,
				new File("C:\\Users\\chris\\Pictures\\JenkinsMeme.png"))
						.withCannedAcl(CannedAccessControlList.PublicRead));

		String imageURLForDatabase = "https://allen-gworek-llc-image-storage.s3.amazonaws.com/reimbursementImages/user"
				+ userID + SUFFIX + currentReimbursementID + ".png";

		String updateQueryForDB = "UPDATE reimbursements SET imageURL = '" + imageURLForDatabase + "' WHERE id = "
				+ currentReimbursementID + ";";
		
		dbc.executeQueryInDatabase(updateQueryForDB);
	}

	public static void createFolder(String bucketName, String folderName, AmazonS3 client) {
		// create meta-data for your folder and set content-length to 0
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);
		// create empty content
		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
		// create a PutObjectRequest passing the folder name suffixed by /
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName + SUFFIX, emptyContent,
				metadata);
		// send request to S3 to create folder
		client.putObject(putObjectRequest);
	}
}
