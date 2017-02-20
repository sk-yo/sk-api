var java  = require('java');
var path = require('path');
java.classpath.push(path.resolve(__dirname, 'lib/sk-java-api-1.0.0.jar'));

module.exports = class  {
	constructor(path) {
		this.path = path;
	}
	
	findClassesByAnnotationName(name) {
		return java.callStaticMethodSync('br.sk.api.Project','findClassesByAnnotationName', this.path, name);
	}
}