var java  = require('java');
var path = require('path');
const _ = require('lodash');
java.classpath.push(path.resolve(__dirname, 'lib/sk-java-api-1.1.0.jar'));

module.exports = {
	/**
	 * Retorna a entidade
	 * 
	 * @param {*} entityName 
	 */
	findByName(entityName) {
		return JSON.parse(java.callStaticMethodSync('br.sk.api.Entities', 'findByName',  process.cwd(), name));
	}

}