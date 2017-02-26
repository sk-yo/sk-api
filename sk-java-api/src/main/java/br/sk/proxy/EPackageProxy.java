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
	public String getClassParentPackageName() {
		if (this.classParentPackageName == null) {
			this.classParentPackageName = getName().substring(0, getName().lastIndexOf("."));
		}
		return this.classParentPackageName;
	}

	@Override
	public String getClassParentPackageDirectory() {
		if (this.classParentPackageDirectory == null) {
			this.classParentPackageDirectory = getClassParentPackageName().replaceAll("\\.", "/");
		}
		return this.classParentPackageDirectory;
	}

}
