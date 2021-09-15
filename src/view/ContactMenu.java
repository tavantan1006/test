package view;

import controller.ContactManagement;
import model.Contact;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactMenu {
    private static final String FAMILY = "Gia đình";
    private static final String COMPANY = "Công ty";
    private static final String OTHER = "Khác";
    public static final String MALE = "Nam";
    public static final String FEMALE = "Nữ";
    public static Scanner scanner = new Scanner(System.in);
    public static ContactManagement contactManagement = new ContactManagement();

    public void runMenuContact() {
        int choice;
        do {
            readFileToList();
            menu();
            choice = getChoice();
            switch (choice) {
                case 1: {
                    addNumberPhoneToContact();
                    break;
                }
                case 2: {
                    deleteNumberPhone();
                    break;
                }
                case 3: {
                    updateInfoContact();
                    break;
                }
                case 4: {
                    showContactInfo();
                    break;
                }
                case 5: {
                    findNumberPhoneInfo();
                    break;
                }
                case 0: {
                    System.exit(0);
                    break;
                }
                default: {
                    System.out.println("HÃY CHỌN SỐ CÓ TRONG MENU");
                    break;
                }
            }
        } while (choice != 0);
    }

    private void writeListToFile() {
        contactManagement.writeFile("contact.txt");
    }

    private void readFileToList() {
        contactManagement.readFile("contact.txt");
    }

    private void findNumberPhoneInfo() {
        System.out.println("Nhập tên hoặc số điện thoại : ");
        String stringFind = scanner.nextLine();
        boolean checkFind = checkFind(stringFind);
        if (checkFind) {
            for (Contact contact : contactManagement.getContacts()) {
                System.out.println(contact);
            }
        } else {
            System.out.println("KHÔNG TÌM THẤY THÔNG TIN ĐÃ NHẬP");
        }
    }

    private boolean checkFind(String stringFind) {
        for (Contact contact : contactManagement.getContacts()) {
            if (!stringFind.contains(contact.getNumberPhone()) ||
                    !stringFind.contains(contact.getFullName())) {
                return true;
            }
        }
        return false;
    }

    private void showContactInfo() {
        contactManagement.displayAll();
    }

    private void updateInfoContact() {
        int index = getIndexFromNumberPhone();
        if (index == -1) {
            System.out.println("KHÔNG THẤY SỐ ĐIỆN THOẠI NÀY TRONG DANH BẠ");
        } else {
            System.out.println("SỬA THÔNG TIN THUÊ BAO");
            Contact contact = setContacInfo(index);
            contactManagement.updateInfo(index, contact);
            System.out.println("CẬP NHẬP THÀNH CÔNG");
            writeListToFile();
        }
    }

    private Contact setContacInfo(int index)     {
        String numberPhone = contactManagement.getContacts().get(index).getNumberPhone();
        String group = inputGroup();
        String fullName = inputFullName();
        String gender = inputGender();
        String address = inputAddress();
        String dateOfBirth = inputDateOfBirth();
        String email = inputEmail();
        return new Contact(numberPhone,group, fullName, gender, address, dateOfBirth, email);
    }

    private void deleteNumberPhone() {
        int index = getIndexFromNumberPhone();
        if (index == -1) {
            System.out.println("KHÔNG THẤY SỐ ĐIỆN THOẠI NÀY TRONG DANH BẠ");
        } else {
            confirmDeleteNumberPhoneInfo(index);
        }
    }

    private void confirmDeleteNumberPhoneInfo(int index) {
        int chocie;
        do {
            menuDelete();
            chocie = getChoice();
            switch (chocie) {
                case 1: {
                    contactManagement.deleteInfo(index);
                    System.out.println("XÓA THÀNH CÔNG");
                    break;
                }
                case 2: {
                    break;
                }
                default: {
                    System.out.println("HÃY CHỌN SỐ CÓ TRONG MENU");
                    break;
                }
            }
        } while (chocie != 1 || chocie != 2);
    }

    private void menuDelete() {
        System.out.println("XÓA SỐ ĐIỆN THOẠI");
        System.out.println("1. Xóa");
        System.out.println("2. Quay lại");
    }

    private int getIndexFromNumberPhone() {
        System.out.println("Nhập số điện thoại : ");
        String numberPhone = scanner.nextLine();
        return contactManagement.findIndex(numberPhone);
    }

    private void addNumberPhoneToContact() {
        Contact contact = createContact();
        contactManagement.addNew(contact);
        writeListToFile();
    }

    private Contact createContact() {
        String numberPhone = inputNumberPhone();
        String group = inputGroup();
        String fullName = inputFullName();
        String gender = inputGender();
        String address = inputAddress();
        String dateOfBirth = inputDateOfBirth();
        String email = inputEmail();
        return new Contact(numberPhone, group, fullName, gender, address, dateOfBirth, email);
    }

    private String inputDateOfBirth() {
        String dateOfBirth = "";
        do {
            System.out.println("Ngày sinh : ");
            dateOfBirth = scanner.nextLine();
            if (dateOfBirth.length() < 10) {
                System.out.println("TRƯỜNG NÀY KHÔNG THỂ BỎ TRỐNG. HÃY NHẬP ĐÚNG ĐỊNH DẠNG dd/mm/yyyy");
            }
        } while (dateOfBirth.length() < 10);
        return dateOfBirth;
    }

    private String inputGender() {
        String gender = "";
        int choice;
        do {
            menuGender();
            choice = getChoice();
            switch (choice) {
                case 1: {
                    gender = MALE;
                    break;
                }
                case 2: {
                    gender = FEMALE;
                    break;
                }
                case 3: {
                    gender = OTHER;
                    break;
                }
                default: {
                    System.out.println("KHÔNG HỢP LỆ MỜI BẠN CHỌN LẠI");
                    break;
                }
            }
        } while (gender.equals(""));
        return gender;
    }

    private void menuGender() {
        System.out.println("CHỌN GIỚI TÍNH");
        System.out.println("1. Nam");
        System.out.println("2. Nữ");
        System.out.println("3. Khác");
    }

    private String inputGroup() {
        String group = "";
        int choice;
        do {
            menuGroup();
            choice = getChoice();
            switch (choice) {
                case 1: {
                    group = FAMILY;
                    break;
                }
                case 2: {
                    group = COMPANY;
                    break;
                }
                case 3: {
                    group = OTHER;
                    break;
                }
                default: {
                    System.out.println("KHÔNG HỢP LỆ MỜI BẠN CHỌN LẠI");
                    break;
                }
            }
        } while (group.equals(""));
        return group;
    }

    private void menuGroup() {
        System.out.println("CHỌN NHÓM DANH BẠ");
        System.out.println("1. Gia đình");
        System.out.println("2. Công ty");
        System.out.println("3. Khác");
    }

    private void menu() {
        System.out.println("MENU");
        System.out.println("1. Thêm số điện thoại vào danh bạ");
        System.out.println("2. Xóa số điện thoại khỏi danh bạ");
        System.out.println("3. Sửa số điện thoại trong danh bạ");
        System.out.println("4. Hiển thị số điện thoại trong danh bạ");
        System.out.println("5. Tìm kiếm số điện thoại trong danh bạ");
        System.out.println("0. Thoát");
    }

    private String inputAddress() {
        String address = "";
        do {
            System.out.println("Địa chỉ");
            address = scanner.nextLine();
            if (address.equals("")) {
                System.out.println("BẠN CHƯA NHẬP TÊN");
            } else if (address.length() < 2) {
                System.out.println("TÊN KHÔNG HỢP LỆ");
            }
        } while (address.length() < 2);
        return address;
    }

    private String inputNumberPhone() {
        String numberPhone;
        final String PHONE = "^[0]{1}+[235789]{1}+\\d{8}$";
        Pattern patternPhone = Pattern.compile(PHONE);
        Matcher matcherPhone;
        int index;
        do {
            System.out.println("Số điện thoại : ");
            numberPhone = scanner.nextLine();
            matcherPhone = patternPhone.matcher(numberPhone);
            index = contactManagement.findIndex(numberPhone);
            if (index != -1) {
                System.out.println("SỐ ĐIỆN THOẠI ĐÃ TỒN TẠI TRONG DANH BẠ");
            }
            if (!matcherPhone.matches()) {
                System.out.println("SỐ ĐIỆN THOẠI KHÔNG HỢP LỆ\nSố điện thoại phải bắt đầu bằng số 0 và có 10 số");
            }
        } while (!matcherPhone.matches() || index != -1);
        return numberPhone;
    }

    private String inputEmail() {
        String email;
        final String EMAIL = "^[a-zA-Z]+[A-Za-z0-9]{7}+@[a-zA-Z]+(\\.com)$";
        Pattern patternMail = Pattern.compile(EMAIL);
        Matcher matcherMail;
        do {
            System.out.println("Email : ");
            email = scanner.nextLine();
            matcherMail = patternMail.matcher(email);
            if (!matcherMail.matches()) {
                System.out.println("EMAIL KHÔNG HỢP LỆ MỜI NHẬP LẠI. EMAIL PHẢI CÓ ÍT NHẤT 8 KÝ TỰ");
            }
        } while (!matcherMail.matches());
        return email;
    }

    private String inputFullName() {
        String fullName;
        final String NAME = "^([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]+)$";
        Pattern patternName = Pattern.compile(NAME);
        Matcher matcherName;
        do {
            System.out.println("Họ và tên : ");
            fullName = scanner.nextLine();
            matcherName = patternName.matcher(fullName);
            if (fullName.equals("")) {
                System.out.println("BẠN CHƯA NHẬP TÊN");
            } else if (fullName.length() < 2 || !matcherName.matches()) {
                System.out.println("TÊN KHÔNG HỢP LỆ! VUI LÒNG NHẬP LẠI");
            }
        } while (fullName.length() < 2 || !matcherName.matches());
        return fullName;
    }


    private int getChoice() {
        int choice = -1;
        try {
            System.out.println("Mời bạn chọn : ");
            String inputChoice = scanner.nextLine();
            choice = Integer.parseInt(inputChoice);
        } catch (NumberFormatException e) {
            System.out.println("HÃY NHẬP VÀO DẠNG SỐ");
        }
        return choice;
    }
}
