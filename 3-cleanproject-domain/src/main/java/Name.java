public final class Name {
	private String name;
    public Name(String name) throws IllegalNameException {
    	validateName(name);
    	this.name = name;
    }
	private void validateName(String name) throws IllegalNameException {
		if (name.length() <= 2) {
			throw new IllegalNameException("Name: "+ name+ " is to short");
        }
		if (!name.matches("[a-zA-ZäöüÄÖÜ]*")) {

		throw new IllegalNameException("Name: "+ name+ " contains Illegal Character");
        }
		
	}
	@Override
	public String toString() {
		return name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Name other = (Name) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
