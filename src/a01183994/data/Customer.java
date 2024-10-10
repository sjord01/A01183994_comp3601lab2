package a01183994.data;

public class Customer {
    private final String id;
    private final String phone;
    private String firstName;
    private String lastName;
    private String streetName;
    private String city;
    private String postalCode;
    private String email;
    private String joinDate;

    public static class Builder {
        // Required parameters
        private final String id;
        private final String phone;

        // Optional parameters - initialized to default values
        private String firstName = "";
        private String lastName = "";
        private String streetName = "";
        private String city = "";
        private String postalCode = "";
        private String email = "";
        private String joinDate = "";

        public Builder(String id, String phone) {
            this.id = id;
            this.phone = phone;
        }

        public Builder firstName(String val) {
            firstName = val;
            return this;
        }

        public Builder lastName(String val) {
            lastName = val;
            return this;
        }

        public Builder streetName(String val) {
            streetName = val;
            return this;
        }

        public Builder city(String val) {
            city = val;
            return this;
        }

        public Builder postalCode(String val) {
            postalCode = val;
            return this;
        }

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder joinDate(String val) {
            joinDate = val;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }

    private Customer(Builder builder) {
        id = builder.id;
        phone = builder.phone;
        firstName = builder.firstName;
        lastName = builder.lastName;
        streetName = builder.streetName;
        city = builder.city;
        postalCode = builder.postalCode;
        email = builder.email;
        joinDate = builder.joinDate;
    }

    
    public final String getId() {
		return id;
	}


	public final String getPhone() {
		return phone;
	}


	public final String getFirstName() {
		return firstName;
	}


	public final String getLastName() {
		return lastName;
	}


	public final String getStreetName() {
		return streetName;
	}


	public final String getCity() {
		return city;
	}


	public final String getPostalCode() {
		return postalCode;
	}


	public final String getEmail() {
		return email;
	}


	public final String getJoinDate() {
		return joinDate;
	}


	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public final void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public final void setStreetName(String streetName) {
		this.streetName = streetName;
	}


	public final void setCity(String city) {
		this.city = city;
	}


	public final void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}


	public final void setEmail(String email) {
		this.email = email;
	}


	public final void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}


	@Override
    public String toString() {
        return "Customer [id=" + id + ", phone=" + phone + ", firstName=" + firstName + ", lastName=" + lastName
                + ", streetName=" + streetName + ", city=" + city + ", postalCode=" + postalCode + ", email=" + email
                + ", joinDate=" + joinDate + "]";
    }
}
