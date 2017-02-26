var java  = require('java');
var path = require('path');
const _ = require('lodash');
java.classpath.push(path.resolve(__dirname, 'lib/sk-java-api-1.0.2.jar'));

module.exports = {
	findClassesByAnnotationName(name) {
		return JSON.parse(java.callStaticMethodSync('br.sk.api.Project','findClassesByAnnotationName', process.cwd(), name));
	},
	
	findClassesNamesByAnnotationName(name) {
		return JSON.parse(java.callStaticMethodSync('br.sk.api.Project','findClassesNamesByAnnotationName', process.cwd(), name));
	},
	
	findClassByName(name) {
		return JSON.parse(java.callStaticMethodSync('br.sk.api.Project','findClassByName', process.cwd(), name));
	},
	
	findAttributeWithAnnotationName(attrs, annotationName) {
		return _.find(attrs, function(attr) { 
            return _.find(attr.annotations, function(ann) {
                 return ann.name === annotationName;
            }); 
        });
	},

}