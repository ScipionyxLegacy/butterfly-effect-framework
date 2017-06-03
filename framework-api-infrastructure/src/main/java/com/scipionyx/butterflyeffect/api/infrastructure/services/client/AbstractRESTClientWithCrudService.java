package com.scipionyx.butterflyeffect.api.infrastructure.services.client;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 
 * @author rmendes
 *
 * @param <ENTITY>
 *            the class for the Crud Service
 */
@NoRepositoryBean
public abstract class AbstractRESTClientWithCrudService<ENTITY, ENTITY_ID_TYPE extends Serializable>
		extends AbstractRESTClientService<ENTITY, ENTITY_ID_TYPE> implements CrudRepository<ENTITY, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public List<ENTITY> findAll() {
		try {
			ENTITY[] body = restTemplate.exchange(calculateURI("findAll"), HttpMethod.GET, HttpEntity.EMPTY, arrayClazz)
					.getBody();
			return Arrays.asList(body);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param filter
	 *            defines the filter for the query
	 * @return returns the entity or null if no entity found
	 * @throws Exception
	 *             if the query can not be executed
	 */
	public ENTITY findOne(Optional<ENTITY> filter) throws Exception {

		if (!filter.isPresent()) {
			List<ENTITY> findAll = findAll();
			return findAll.get(0);
		}

		ENTITY entity = filter.get();
		Map<String, Object> map = new HashMap<>();
		map.put("", entity);
		findOneBy(map);

		return null;
	}

	/**
	 * 
	 * @param map
	 *            set of parameters to be queried upon to
	 * @return returns the entity or null if no entity found
	 * @throws Exception
	 *             if the query can not be executed
	 */
	public ENTITY findOneBy(Map<String, Object> map) throws Exception {
		try {

			HttpHeaders headers = new HttpHeaders();

			for (Entry<String, Object> entry : map.entrySet()) {
				headers.add(entry.getKey(), entry.getValue().getClass().getName());
			}

			final HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

			ENTITY body = restTemplate.exchange(calculateURI("findOne"), HttpMethod.PUT, entity, clazz).getBody();

			return body;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 
	 * @param map
	 *            set of parameters for the query
	 * @param orderBy
	 *            Parameter to order by
	 * @return returns the List of entity or null if no entity found
	 * @throws Exception
	 *             if the query can not be executed
	 */
	public List<ENTITY> findAllByOrderBy(Map<String, Value> map, String orderBy) throws Exception {
		return findAllByOrderBy(map, orderBy, true);
	}

	/**
	 * 
	 * @param map
	 *            set of parameters for the query
	 * @param orderBy
	 *            Parameter to order by
	 * @param asc
	 *            indicates if the order by is asc
	 * @return returns the list of entity or null if no entity found
	 * @throws Exception
	 *             if the query can not be executed
	 */
	public List<ENTITY> findAllByOrderBy(Map<String, Value> map, String orderBy, boolean asc) throws Exception {
		try {

			HttpHeaders headers = new HttpHeaders();
			headers.add("orderBy", orderBy);
			headers.add("orderByAsc", asc ? "ASC" : "DES");

			final HttpEntity<Map<String, Value>> entity = new HttpEntity<>(map, headers);

			ENTITY[] body = restTemplate.exchange(calculateURI("findAllByOrderBy"), HttpMethod.PUT, entity, arrayClazz)
					.getBody();

			return Arrays.asList(body);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 
	 * @param orderBy
	 *            Parameter to order by
	 * @return returns the List of entity or null if no entity found
	 */
	public List<ENTITY> findAllOrderBy(String orderBy) {

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<?> entity = new HttpEntity<>(headers);

		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUri(calculateURI("findAllOrderBy"))
					.queryParam("orderBy", orderBy);
			ENTITY[] body = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, arrayClazz)
					.getBody();
			return Arrays.asList(body);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <S extends ENTITY> S save(S s) {

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<?> entity = new HttpEntity<>(s, headers);

		try {
			return (S) restTemplate.exchange(calculateURI("save"), HttpMethod.PUT, entity, clazz).getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <S extends ENTITY> Iterable<S> save(Iterable<S> entities) {

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<?> entity = new HttpEntity<>(entities, headers);

		try {
			ENTITY[] body = restTemplate.exchange(calculateURI("saveAll"), HttpMethod.PUT, entity, arrayClazz)
					.getBody();
			return (Iterable<S>) Arrays.asList(body);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 */
	@Override
	public ENTITY findOne(Long id) {

		//
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		//
		HttpEntity<?> entity = new HttpEntity<>(id, headers);

		try {

			ResponseEntity<ENTITY> exchange = restTemplate.exchange(calculateURI("findOne").toString() + "/{id}",
					HttpMethod.GET, entity, clazz, id);

			if (exchange.getStatusCode().equals(HttpStatus.OK)) {
				return exchange.getBody();
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * @param parameter
	 *            parameter
	 * @return returns the entity or null if no entity found
	 */
	@Deprecated
	public ENTITY findOneBy(CrudParameter parameter) {
		HttpEntity<?> entity = new HttpEntity<>(parameter, new HttpHeaders());
		try {
			return restTemplate.exchange(calculateURI("findOneBy"), HttpMethod.GET, entity, clazz).getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param parameter
	 *            parameter
	 * @return returns the entity or null if no entity found
	 */
	@Deprecated
	public ENTITY findOneBy(List<CrudParameter> parameter) {
		HttpEntity<?> entity = new HttpEntity<>(parameter, new HttpHeaders());
		try {
			return restTemplate.exchange(calculateURI("findOneBy"), HttpMethod.GET, entity, clazz).getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<ENTITY> findAll(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	@Override
	public long count() {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUri(calculateURI("count")).queryParam("all",
					"true");
			return restTemplate.getForObject(builder.build().toUriString(), Long.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 
	 */
	@Override
	public void delete(Long id) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUri(calculateURI("deleteById")).queryParam("id",
					id);
			restTemplate.delete(builder.build().toUriString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	@Override
	public void delete(ENTITY entity) {
		try {
			HttpEntity<?> httpEntity = new HttpEntity<>(entity, new HttpHeaders());
			restTemplate.exchange(calculateURI("deleteEntity"), HttpMethod.DELETE, httpEntity, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 */
	@Override
	public void delete(Iterable<? extends ENTITY> entities) {
		try {
			HttpEntity<?> entity = new HttpEntity<>(entities, new HttpHeaders());
			restTemplate.exchange(calculateURI("deleteList"), HttpMethod.DELETE, entity, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
