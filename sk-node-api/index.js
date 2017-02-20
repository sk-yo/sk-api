var java  = require('java');
var path = require('path');
java.classpath.push(path.resolve(__dirname, 'lib/sk-java-api-1.0.0.jar'));

module.exports = {
	findClassesByAnnotationName(name) {
		return java.callStaticMethodSync('br.sk.api.Project','findClassesByAnnotationName', process.cwd(), name);
	},
	
	findClassesNamesByAnnotationName(name) {
		return java.callStaticMethodSync('br.sk.api.Project','findClassesNamesByAnnotationName', process.cwd(), name);
	},
	
	findClassByName(name) {
		return java.callStaticMethodSync('br.sk.api.Project','findClassByName', process.cwd(), name);
	}
}