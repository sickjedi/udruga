import { BaseEntity } from './../../shared';

export class Customer implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public oib?: string,
        public cellphone?: string,
        public email?: string,
        public datecreated?: any,
        public lastupdated?: any,
        public ids?: BaseEntity[],
    ) {
    }
}
