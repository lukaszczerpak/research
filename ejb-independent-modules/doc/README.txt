
It is important to disable non-jndi ejb binding in glassfish before deploying this project:

asadmin set server.ejb-container.property.disable-nonportable-jndi-names="true"

