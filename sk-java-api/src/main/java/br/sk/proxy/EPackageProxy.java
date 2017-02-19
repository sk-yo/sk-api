package br.sk.proxy;

import com.thoughtworks.qdox.model.JavaPackage;

import br.sk.model.EPackage;

public class EPackageProxy extends EPackage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JavaPackage javaPackage;

	public EPackageProxy(JavaPackage javaPackage) {
		super();
		this.javaPackage = javaPackage;
	}

	@Override
	public String getName() {
		if (this.name == null) {
			this.name = javaPackage.getName();
		}
		return this.name;
	}

	@Override
	public String getDirectory() {
		if (this.directory == null) {
			this.directory = javaPackage.getName().replaceAll("\\.", "/");
		}
		return this.directory;
	}

	@Override
	public String getParentName() {
		if (this.parentName == null) {
			this.parentName = getName().substring(0, getName().lastIndexOf("."));
		}
		return this.parentName;
	}

	@Override
	public String getParentDirectory() {
		if (this.parentDirectory == null) {
			this.parentDirectory = getParentName().replaceAll("\\.", "/");
		}
		return this.parentDirectory;
	}

}
