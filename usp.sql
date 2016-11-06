CREATE DEFINER=`root`@`localhost` PROCEDURE `uspgetqualid`(d VARCHAR(45))
begin
select QualificationID from qualification where Description=d;
end

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `spUpdateData`(in_password varchar(45), in_username varchar(20), in_email2 varchar(128), 
in_first_name varchar(45), in_last_name varchar(45), in_about_me text, 
in_photoURL1 varchar(255), in_photoURL2 varchar(255), in_photoURL3 varchar(255), 
		in_street_no varchar(20), in_street_name varchar(60), in_MajorMuniciality varchar(45), 
		in_GoverningDistrict varchar(45), in_PostalArea varchar(45), 
        in_phone char(12), in_usertype varchar(20), in_Status tinyint(1))
BEGIN

update User set Password = in_password,
 email2 = in_email2,
FirstName=in_first_name,
LastName=in_last_name,
AboutMe=in_about_me,
PhotoURL1=in_photoURL1,
PhotoURL2=in_photoURL2,
PhotoURL3=in_photoURL3,
StreetNumber=in_street_no,
StreetName=in_street_name,
MajorMunicipality=in_MajorMuniciality,
GoverningDistrict=in_GoverningDistrict,
PostalArea=in_PostalArea,
Status=in_Status where username = in_username;

if in_usertype = 'Administrator'
then
update Administrator set phone=in_phone where username=in_username;
elseif in_usertype = 'Moderator'
then 
update Moderator set phone = in_phone where username = in_username;
end if;
END$$
DELIMITER ;
